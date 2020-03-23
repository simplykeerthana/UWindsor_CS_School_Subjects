#ifndef MYSTDIO_H_
#define MYSTDIO_H_

#include "myfile.h"

enum { MYEOF = -1 };

MYFILE * myfopen(const char * path, const char * mode);
MYFILE * myfdopen(int fd, const char * mode);
int myfclose(MYFILE * fp);

int myfileno(MYFILE * fp);

int myfgetc(MYFILE * fp);
char * myfgets(char * buf, int n, MYFILE * fp);

int myfputc(int c, MYFILE * fp);
int myfputs(const char * str, MYFILE * fp);

int myferror(MYFILE * fp);
int myfeof(MYFILE * fp);
void myclearerr(MYFILE * fp);

long myftell(MYFILE * fp);
int myfseek(MYFILE * fp, long offset, int whence);
void myrewind(MYFILE * fp);

#endif // MYSTDIO_H_
