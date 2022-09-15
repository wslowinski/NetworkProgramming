# UDP Server
The User Datagram Protocol (UDP) is one of the core members of the Internet 
protocol suite. With UDP, computer applications can send messages, in this case 
referred to as datagrams, to other hosts on an Internet Protocol (IP) network. 
Prior communications are not required in order to set up communication channels 
or data paths. This directory contains UDP server implementation that sum the 
sequence of natural numbers. The process can be broken down into the following 
steps:  

**UDP Server:**  
* Create a UDP socket.
* Bind the socket to the server address.
* Wait until the datagram packet arrives from the client.
* Process the datagram packet and send a reply to the client.
* Go back to Step 3.

## Necessary POSIX functions  
```cpp
int socket(int domain, int type, int protocol);                        // Creates an unbound socket in the specified  
                                                                       // domain, and returns socket file descriptor.
                                                                       
int bind(int sockfd, const struct sockaddr *addr, socklen_t addrlen);  // Assigns address to the unbound socket.

ssize_t sendto(int sockfd, const void *buf, size_t len, int flags,
               const struct sockaddr *dest_addr, socklen_t addrlen);   // Send a message on the socket.

ssize_t recvfrom(int sockfd, void *buf, size_t len, int flags,
                 struct sockaddr *src_addr, socklen_t *addrlen);       // Receive a message from the socket.

int close(int fd);                                                     // Close a file descriptor
```

## Content
* ```UDPServer.cpp``` - UDP server implementation that sum the sequence of natural numbers;

## Technologies
Project is created with ```C++20```.

## Setup
1. To run this project, install it locally;
2. The following make command will help you in compiling the Makefile into an executable program:
```
$ make
```
3. Finally, we are ready to run the executable file we created in the last step through the Terminal. Run the following command to do so:
``` 
$ ./UDPServer.x <PORT>
```
4. In another terminal please execute following command:
```
$ printf "1 2 3 4 5" | ncat -u 127.0.0.1 <PORT>
```
