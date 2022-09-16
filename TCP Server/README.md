# UDP Server
The Transmission Control Protocol (TCP) is one of the main protocols of the 
Internet protocol suite. TCP provides reliable, ordered, and error-checked 
delivery of a stream of octets (bytes) between applications running on hosts 
communicating via an IP network. Major internet applications such as the World 
Wide Web, email, remote administration, and file transfer rely on TCP, which is 
part of the Transport Layer of the TCP/IP suite. SSL/TLS often runs on top 
of TCP. This directory contains TCP server implementation that sum the 
sequence of natural numbers. The process can be broken down into the following 
steps:  

**TCP Server:**  
* Create TCP socket.
* Bind the socket to server address.
* Put the server socket in a passive mode, where it waits for the client to approach the server to make a connection.
* At this point, connection is established between client and server, and they are ready to transfer data.
* Go back to Step 3.

## Necessary POSIX functions  
```cpp
int socket(int domain, int type, int protocol);                        // Create an endpoint for communication.
int bind(int sockfd, const struct sockaddr *addr, socklen_t addrlen);  // Bind a name to a socket.
int listen(int sockfd, int backlog);                                   // Listen for connections on a socket.
int accept(int sockfd, struct sockaddr *addr, socklen_t *addrlen);     // Accept a connection on a socket. 
ssize_t read(int fd, void *buf, size_t count);                         // Read from a file descriptor.
ssize_t send(int sockfd, const void *buf, size_t len, int flags);      // Send a message on a socket.
int close(int fd);                                                     // Close a file descriptor
```

## Content
* ```TCPServer.cpp``` - TCP server implementation that sum the sequence of natural numbers;

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
$ ./TCPServer.x <PORT>
```
4. In another terminal please execute following commands:
```
$ printf "10 200 300 400\r\n5000 6\r\n"|socat stdio tcp4:127.0.0.1:<PORT> > out.txt
```
or
```
$ printf "10 200 300 400\r\naa bbb c\r\n"|socat stdio tcp4:127.0.0.1:<PORT>|od -A d -t u1 -t c
```
