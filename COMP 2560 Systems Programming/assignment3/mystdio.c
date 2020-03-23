/*
 
 Name: Keerthana Madhavan
 Date: 12/23
 
 */

#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <stdlib.h>
#include "myfile.h"
#include <string.h>
#include <errno.h>

//#define BUFSIZE 1094
#define READONLY_MODE 0
#define WRITEONLY_MODE 1


MYFILE * myfopen(const char * path, const char * mode)
{
    MYFILE *fp=(MYFILE*)malloc(sizeof(MYFILE));
    
    if(!fp)
    {
        errno = ENFILE;
        return NULL;
    }
    
    if(fp!=NULL){
        if(strcmp(mode, "r")==0)
        {
            if((fp->fd= open(file, O_RDONLY))<0)
            {
                free(fp);
                return NULL;
            }
            fp->mode=READONLY_MODE;
        }
        else if(strcmp(mode, "w")==0)
        { //if its not write mode, then set it to write only
            if((fp->fd=open(file, O_WRONLY|O_CREAT|O_TRUNC, 0644))<0)
            {
                free(fp);
                return NULL;
            }
            fp->mode=WRITEONLY_MODE;
        }
        else
        {
            fprintf(2, "not supported mode : %s\n", mode);
            free(fp);
            return NULL;
        }
        fp->offset=fp->length=0;
    }
    return fp;
}
MYFILE * myfdopen(int fd, const char * mode)
{
    MYFILE *stream;
    int flagStream;
    
    switch (*mode)
    {
        case 'r':
            flagStream = _IORD; // file flags
            break;
        case 'w':
            flagStream = _IOWR;
            break;
        case 'a':
            flagStream = _IOWR;
            break;
        default:
            errno = EINVAL;
            break;
    }
    
    while(*mode++)
    {
        switch (*mode)
        {
            case '+':
                flagStream |= _IORW;
                flagStream &= ~(_IORD | _IOWR);
                break;
                
            default:
                break;
        }
        
    }
    
   MYFILE *fp = malloc(sizeof(MYFILE));
    if(!fp)
    {
        errno = ENFILE;
        return NULL;
    }
}

int myfclose(MYFILE * fp)
{
    if(fp==NULL)
        return feof(fp);
    if(fp->mode == WRITEONLY_MODE){
        write(fp->fd, fp->buf, fp->length);
        fp->length=0;
    }
    if(fp->mode==READONLY_MODE){
        fp->offset=fp->length=0;
    }
    if(close(fp->fd)<0){
        free(fp);
        return feof(fp);
    }
    free(fp);
    return 0;
}

int myfileno(MYFILE * fp)
{
    //fd = open(fp, fp->mode);
    // you have to explicitylt write the number to the screen using write.
    //return fp->fd;
    
    
    
    extern void __stdio_check_funcs __P ((MYFILE *fp));
    
    if (! __validfp (fp))
    {
        __set_errno (EINVAL);
        return -1;
    }
    
    __stdio_check_funcs (fp);
    
    if (fp->fd == NULL)
    {
        #ifdef EOPNOTSUPP
                __set_errno (EOPNOTSUPP);
        #else
                __set_errno (ENOSYS);
        #endif
        return -1;
    }
    
    return fp->fd
    
    

}


int myfgetc(MYFILE * fp)
{
    if(fp == NULL)
    {
        return feof(fp);
    }
    if(fp->offset==fp->length)
    {
        if((fp->length=read(fp->fd, fp->buf, BUFSIZE))<1)
        {
            return feof(fp);
        }
        fp->offset = 0;
    }
    return fp->buf[fp->offset++];
    
}
char * myfgets(char * buf, int n, MYFILE * fp)
{
    char *ptr = buf;
    int chr;
    
    if(n<=0)
    {
        return NULL;
    }
    
    while(--n)
    {
        if((chr = getc(fp)) == feof(fp))
        {
            if(ptr == buf) // to travers bufferef buf
            {
                return NULL;
            }
            break;
        }
        
        if((*ptr++ = chr) == '\n')
        {
            break; // util a new line character
        }
        
    }
    
    *ptr = '\0';//set to null character
    return buf;
    
}

int myfputc(int c, MYFILE * fp)
{
    if(fp->length == BUFSIZE){
        write(fp->fd, fp->buf, BUFSIZE);
        fp->length=0;
    }
    fp->buf[fp->length++]=c;
    
}
int myfputs(const char * str, MYFILE * fp)
{
    int length;
    int write;
    
    length = strlen(str);
    
    if(fp->flag & _IONBF)
    {
        char buf[bufsize];
        
        _stbuf(fp, buf, bufsize)
        write = fwrite(str, 1, length, fp);
        _ftbuf(fp);
    }
    else
    {
        write = fwrite(str, 1, length, fp);
    }
    
}

//this

int myferror(MYFILE * fp)
{
    //if()
  if(!__validfp(fp))
  {
      __set_errno (ENIVAL);
      return -1;
    
  }
    
    return fp->__error;
    
}
int myfeof(MYFILE * fp)
{
    // seek throught the file and when you reach it end it.
    if(fp->length >= fp->buf[BUFSIZE])
    {
        return 0;
    }
    else
    {
        return 1;
    }
    
}
void myclearerr(MYFILE * fp)
{
    fp->flag &= ~(_IOERR | _IOEOF); // clears both the erroe and eof in mem
}

long myftell(MYFILE * fp)
{
    long filePosition;
    
    if(fp->counter < 0)
    {
        fp->counter = 0;
    }
    
    if((filePosition = tell(fileno(fp))) <0L)
    {
        return -1;
    }
    
    if(!bifbuf(fp))
    {
        return filePosition - fp->counter;
    }
    else
    {
        if(fp->flag & _IOWR)
        {
            filePosition += (fp->ptr - fp->base);
        }
    }
}

int myfseek(MYFILE * fp, long offset, int whence)
{
    if(whence != SEEK_SET && whence != SEEK_CUR && whence != SEEK_END)
    {
        ernno = EINVAL;
        return -1;
    }
    
    fp->flag &= ~_IOEOF;
    
    if(whence == SEEK_CUR && (fp->flag & _IORD))
    {
        offset -= fp->counter;
    }
    
    fflush(fp);
    
    if(fp->flag & _IORW)
    {
        fp->flag &= ~(_IOWR | _IORD);
        
    }
    
    return lseek(fileno(fp), offset, whence) < 0 ? -1 : 0;
}

void myrewind(MYFILE * fp)
{
    myfseek(fp, 0, SEEK_SET);
    myclearerr(fp);
}
