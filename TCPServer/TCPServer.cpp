#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <inttypes.h>
#include <vector>
#include <string>

#define BUF_SIZE 256

using namespace std;

vector<const char*> bufToVector(const char* buf) {
    char* str = new char[strlen(buf) + 1];
    strcpy(str, buf);
    vector<const char*> v;    
    char* pch = strtok(str, "\r\n");
    while (pch != nullptr) {
        v.push_back(pch);
        pch = strtok(nullptr, "\r\n");
    }
    return v;   
}

vector<const char*> resultAsVector(vector<const char*> v) {
    vector<const char*> result;
    for (unsigned int i = 0; i < v.size(); i++) {
        bool flag = false;
        vector<intmax_t> currentVector;
        vector<int> idx;
        if ((int)v[i][0] == 32 || (int)v[i][strlen(v[i]) - 1] == 32) {
            result.push_back("ERROR\r\n");
            continue;;
        }
        for (unsigned int j = 0; j < strlen(v[i]); j++) {
            if (((int)v[i][j] <= 47 || (int)v[i][j] >= 58) && (int)v[i][j] != 32 
                && (int)v[i][j] != 10 && (int)v[i][j] != 13) {
                result.push_back("ERROR\r\n");
                flag = true;
                break;
            }
            if ((int)v[i][j] == 32) idx.push_back(j);
        }
        if (flag) continue;
        currentVector.push_back(atoll((const char*)(v[i])));
        for (unsigned int k = 0; k < idx.size(); k++) {
            currentVector.push_back(atoll((const char*)(v[i] + idx[k])));
        }
        intmax_t sum = 0;
        for (unsigned int it = 0; it < currentVector.size(); it++) {
            if (sum < INTMAX_MAX)
                sum += currentVector[it];
            else {
                result.push_back("ERROR\r\n");
                flag = true;
                break;
            }
        }
        if (flag) continue;
        string str = to_string(sum) + "\r\n";
        char* cstr = new char[str.length() + 1];
        strcpy(cstr, str.c_str());
        result.push_back(cstr);
        flag = false;
    } 
    return result;   
}

const char* resultAsCharArray(vector<const char*> v) {
    string str = "";
    for (int i = 0; i < v.size(); i++) {
        str += v[i];
    }
    char* cstr = new char[str.length() + 1];
    strcpy(cstr, str.c_str());
    return cstr;    
}

int main(int argc, char **argv) {
    int sock_fd, newsock_fd, port_no;
    socklen_t cli_len;
    char buf[BUF_SIZE];
    struct sockaddr_in serv_addr, cli_addr;
    int n;
    if (argc < 2) {
       perror("[ERROR] No port provided");
       exit(0);
    }
    if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("[ERROR] Socket");
        exit(0);
    }
    memset(&serv_addr, 0, sizeof(serv_addr));
    memset(buf, 0, sizeof(buf)); 
    port_no = atoi(argv[1]);
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(port_no);
    if (bind(sock_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) < 0) {
        perror("[ERROR] Bind");
        exit(0);
    }
    if (listen(sock_fd, 5) != 0) {
        perror("[ERROR] Bind");
        exit(0);    
    }
    cli_len = sizeof(cli_addr);
    system("clear");
    printf("*** \033[1;33mServer is started at port %s\033[0m ***\n", argv[1]);
    unsigned int counter = 1;
    while (true) {
        if ((newsock_fd = accept(sock_fd, (struct sockaddr*)&cli_addr, (socklen_t*)&cli_len)) < 0) {
            perror("[ERROR] Accept");
            exit(0);
        }
        bzero(buf, (size_t)BUF_SIZE);
        if ((n = read(newsock_fd, buf, BUF_SIZE)) < 0) {
            perror("[ERROR] Read");
            exit(0);
        }
        vector<const char*> v = resultAsVector(bufToVector(buf));
        if (send(newsock_fd, resultAsCharArray(v), strlen(resultAsCharArray(v)), 0) < 0) {
            perror("[ERROR] Send");
            exit(0);
        }
        else printf("Data was sent: %d\n", counter);
        if (close(newsock_fd) < 0) {
            perror("[ERROR] Close");
            exit(0);
        }
        counter++;        
    }
    if (close(sock_fd)) {
        perror("[ERROR] Close");
        exit(0);   
    }
    return 0; 
}