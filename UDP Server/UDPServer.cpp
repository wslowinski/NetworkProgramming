#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <limits.h>
#include <stdint.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <inttypes.h>
#include <vector>
#include <string>

#define BUFLEN 256

using namespace std;

const char* array_sum(const char* buf) {
    vector<intmax_t> v;
    vector<int> idx;
    for (int i = 0; i < strlen(buf); i++) {
        if (((int)buf[i] <= 47 || (int)buf[i] >= 58) && (int)buf[i] != 32 
        && (int)buf[i] != 10) {
            return "ERROR\n";
        }
        if ((int)buf[i] == 32) idx.push_back(i);
    }
    v.push_back(atoll((const char*)(buf)));
    for (int i = 0; i < idx.size(); i++) {
        v.push_back(atoll((const char*)(buf + idx[i])));
    }
    intmax_t sum = 0;
    for (int i = 0; i < v.size(); i++) {
        if (sum < INTMAX_MAX)
            sum += v[i];
        else return "ERROR\n";
    }
    string str = to_string(sum) + "\n";
    char* cstr = new char[str.length() + 1];
    strcpy (cstr, str.c_str());
    return cstr;
}

int main(int argc, char** argv) {
    if (argc < 2) {
        perror("[Error] Please give me a port number");
        exit(1);
    }
    else {
        struct sockaddr_in si_me, si_other;
        int s, slen = sizeof(si_other), recv_len;
        if ((s = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
            perror("[Error] Socket");
            exit(1);
        }
        memset((char*)&si_me, 0, sizeof(si_me));
        si_me.sin_family = AF_INET;
        si_me.sin_port = htons(atoi(argv[1]));
        si_me.sin_addr.s_addr = htonl(INADDR_ANY);
        if (bind(s, (struct sockaddr*)&si_me, sizeof(si_me)) == -1) {
            perror("[Error] Bind");
            exit(1);
        }
        system("clear");
        printf("*** \033[1;33mServer is started at port %s\033[0m ***\n", argv[1]);
        int counter = 1;
        while (1) {
            char* buf = new char[BUFLEN];
            if ((recv_len = recvfrom(s, buf, BUFLEN, 0, (struct sockaddr*)&si_other, (socklen_t *)&slen)) == -1) {
                perror("[Error] Recvfrom");
                exit(1);
            }
            printf("Data was sent: %d\n", counter);
            const char* result = array_sum(buf);
            if (sendto(s, result, strlen(result), 0, (struct sockaddr*)&si_other, slen) == -1) {
                perror("[Error] Sendto");
                exit(1);
            }
            counter++;
            delete buf;
        }
        if (close(s) == -1) {
            perror("[Error] Close");
            exit(1);  
        }
    }
    return 0;
}
