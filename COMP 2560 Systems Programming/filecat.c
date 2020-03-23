
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h> //for file descriptor
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>

#define BUFFER_SIZE 16


extern int errno;

void printError(const char * msg);

int main(int argc, char *argv[])
{
   // int  fd;
    //int  fd1;
    
    int fd, fd1;
    
    
    char buf[BUFFER_SIZE];
    char c;
    int file_size;
    int errnum;
    
    if(argc != 3)
    {
        fprintf(stderr, "Error: use: ./cat filename outputFile\n", errno);
        return -1;
    }
    
    fd = open(argv[1], O_RDONLY);
    fd1 = open(argv[2], O_WRONLY | O_APPEND | O_CREAT , S_IRUSR);
    

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
       // write(1, &buf, file_size);
            write(fd1, &buf, file_size);
        //  printError(strerror(errno));
        
    }
    
   
 
    
    printError(strerror(errno));

    close(fd);
    
    close(fd1);
    
    
    return 0;
}

void printError(const char * msg)
{
    write(2, "Error: ", 7);
    write(2, msg, strlen(msg));
    write(2, "\n", 1);
}
