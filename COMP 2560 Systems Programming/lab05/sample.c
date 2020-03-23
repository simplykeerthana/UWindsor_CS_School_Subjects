#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void sigint_handler(int);

int  main(void)
{
    signal(SIGINT, sigint_handler);
    
    while (1){
        pause();
    }
    return 0;
}

void sigint_handler(int sig)
{
    /*do something*/
    printf("killing process %d\n",getpid());
    exit(0);
}
