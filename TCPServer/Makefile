.SUFFIXES: .c .cpp .o .x .h
DIR = `basename $(CURDIR)`

SERVER = TCPServer

EXEC_SERVER  = $(SERVER).x  
OBJS_SERVER  = $(SERVER).o
LIBS_SERVER  = 

CXXFLAGS = -std=c++14
LFLAGS = -std=c++14

CXX = g++
LD = $(CXX)

%.o: %.c %.h
	$(CXX) $(CXXFLAGS) -c $<

%.o: %.c
	$(CO) $(CXXFLAGS) -c $<

.PHONY: all
all: $(EXEC_SERVER)

$(EXEC_SERVER): $(OBJS_SERVER) $(LIBS_SERVER)
	$(LD) -o $@ $(LFLAGS) $^

.PHONY: clean tar
clean:                                                     
	rm -f *.o  *~ *.a *.so *.x
	
zip: clean
	(cd ../; zip -r VSlovinskyi_TCPServer.zip  $(DIR) )