#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h> //for file descriptor
#include <errno.h>
#include <string.h>

#define BUFFER_SIZE 16

extern int errno;

void printError(const char * msg);

int main(int argc, char *argv[])
{
     int fd;
    
    char buf[BUFFER_SIZE];
    int file_size;
    int errnum;
    
    if(argc < 2)
    {
        fprintf(stderr, "Error: use: ./cat filename\n", errno);
        return -1;
    }
    
    fd = open(argv[1], O_RDONLY);
    
    if(fd == -1)
    {
        fprintf(stderr, "Error: %s: file not found \n", argv[1]);
        return -1;
    }
    
    if(fd == 0)
    {
        errnum = errno;
        fprintf(stderr, "ernno: %d\n", errno);
        perror("error");
        fprintf(stderr, "Error Opening file: %s\n", strerror(errnum));
        
        return errno;
    }
    
    while((file_size = read(fd, buf, BUFFER_SIZE))>0)
    {
        write(1, &buf, file_size);
        
      //  printError(strerror(errno));
       
    }
    
    printError(strerror(errno));
    
    close(fd);
    
    return 0;
}


void printError(const char * msg)
{
    write(2, "Error: ", 7);
    write(2, msg, strlen(msg));
    write(2, "\n", 1);
}
