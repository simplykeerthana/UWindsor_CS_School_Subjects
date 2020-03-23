
#define BUFSIZE 1024

#ifndef _MYSTDIO_H
#define _MYSTDIO_H

typedef struct
{
    int fd;
    
    char *ptr;
    char *base;
    int counter;
    int flag;
    int file;
    int characbuf;
    int bufsize;
    char *temFileNm;
    
    char mode;
    char buf[BUFSIZE];
    int offset; // to move around the file
    int length;
}MYFILE;

#endif //if file is not defined
