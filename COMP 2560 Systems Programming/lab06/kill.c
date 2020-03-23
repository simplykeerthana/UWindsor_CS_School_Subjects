#include <signal.h>
#include <stdlib.h>
#include <stdio.h>

static pid_t pid;

static void sig_usr1(int signo);
static void sig_usr2(int signo);
static void sig_int(int signo);

int main(int argc, char * argv[])
{
    // set signal handlers for SIGUSR1 and SIGUSR2
    if(signal(SIGUSR1, sig_usr1) == SIG_ERR ||
       signal(SIGUSR2, sig_usr2) == SIG_ERR)
    {
        fprintf(stderr, "%s: error: can't catch signals\n", argv[0]);
        exit(1);
    }
    
    // fork
    pid = fork();
    
    if(pid > 0)
    {
        // set signal handler for SIGINT
        if(signal(SIGINT, sig_int) == SIG_ERR)
        {
            fprintf(stderr, "%s: error: can't catch signal\n", argv[0]);
            exit(1);
        }
        
        // wait for the child - this will still be interrupted by signals
        wait(NULL);
        puts("<PARENT> All done!");
    }
    else
    {
        while(1)
            pause();
    }
    
    return 0;
}

static void sig_usr1(int signo)
{
    if(pid > 0)
    {
        puts("<PARENT> Got it! Sending SIGUSR2 to my group.");
        kill(0, SIGUSR2);
    }
    else
    {
        puts("<CHILD> I'll forward your SIGUSR1 to my parent this time, but not again!");
        kill(getppid(), signo);
        signal(SIGUSR1, SIG_IGN);
    }
}

static void sig_usr2(int signo)
{
    printf("<%s> Got a SIGUSR2!\n", pid > 0 ? "PARENT" : "CHILD");
}

static void sig_int(int signo)
{
    puts("<PARENT> Hang on! I need to kill my child first.");
    kill(pid, signo);
}
