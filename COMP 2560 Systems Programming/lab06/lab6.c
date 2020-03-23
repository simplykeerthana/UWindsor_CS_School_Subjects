#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <signal.h>
#include <sys/wait.h>

enum { R_OKOK = 0, R_EXIT = -1, R_ERROR = -2 };
int status;
volatile pid_t fg_pid;
volatile size_t bg_count;

int prompt(const char * ps, int (*handler)(const char *));
int handler(const char * str);

static void terminal(int signo);
static void bg_wait(int signo);

int main(int argc, char * argv[])
{
    status = 0;
    fg_pid = 0;
    bg_count = 0;
    
    if(isatty(fileno(stdin)))
    {
        // set up signal handlers
        if(signal(SIGINT, terminal) == SIG_ERR ||
           signal(SIGCHLD, bg_wait) == SIG_ERR)
        {
            fprintf(stderr, "%s: cannot register signal handler\n", argv[0]);
            return 1;
        }
        
        // interactive terminal - display prompt
        while(prompt(">>", &handler) != R_EXIT);
    }
    else
    {
        // input from a file - execute script and exit
        char str[80];
        do
        {
            fgets(str, 80, stdin);
            
            if(feof(stdin))
            {
                break;
            }
            
            str[strlen(str) - 1] = '\0';
        }
        while(handler(str) != R_EXIT);
    }
    return 0;
}

int prompt(const char * ps, int (*handler)(const char *))
{
    char str[80];
    fputs(ps, stdout);
    fputc(' ', stdout);
    fflush(stdout);
    fgets(str, 80, stdin);
    
    if(feof(stdin))
    {
        putchar('\n');
        return R_EXIT;
    }
    
    str[strlen(str) - 1] = '\0';
    return (*handler)(str);
}

int handler(const char * str)
{
    // no-ops
    if(str[0] == '\0' || str[0] == '\n' || str[0] == '#')
    {
        return R_OKOK;
    }
    
    if(strcmp(str[0], "cd"))
    {
        chdir(str[1]);
        
        perror("Couldn't change directory");
    
    }
    
    // built-in commands
    if(!strcmp(str, "exit"))
    {
        return R_EXIT;
    }
    else if(!strcmp(str, "status"))
    {
        printf("%d\n", WEXITSTATUS(status));
        return R_OKOK;
    }
    
    // not a built-in command - tokenize
    char * tstr = strdup(str);
    char * args[64], * arg;
    char ** next = args;
    arg = strtok(tstr, " ");
    while(arg != NULL)
    {
        *next++ = arg;
        arg = strtok(NULL, " ");
    }
    *next = NULL;
    
    // determine if this is a background process
    int background = 0;
    if(!strcmp(*(next - 1), "&"))
    {
        *(next - 1) = NULL;
        background = 1;
    }
    
    // fork
    pid_t pid = fork();
    
    // set process group id of child
    setpgid(pid, pid);
    
    if(pid > 0)
    {
        // parent
        if(background)
        {
            // increment count of background processes
            bg_count++;
            
            // print PID of background process
            printf("%d\n", pid);
        }
        else
        {
            // wait for foreground process
            fg_pid = pid;
            waitpid(fg_pid, &status, 0);
            fg_pid = 0;
            
            // clean up any background processes that terminated in the meantime
            raise(SIGCHLD);
        }
        return R_OKOK;
    }
    else
    {
        // child - exec the command
        if(execvp(args[0], args) == -1)
        {
            fprintf(stderr, "error: %s\n", strerror(errno));
            free(tstr);
            exit(1);
        }
    }
}

static void bg_wait(int signo)
{
    // only attempt to wait if there is no foreground process (so we don't
    // accidentally wait for the foreground process here)
    if(!fg_pid)
    {
        int i;
        for(i = bg_count; i > 0; --i)
        {
            // decrement count of background processes if wait succeeds
            if(waitpid(-1, NULL, WNOHANG) > 0)
            {
                bg_count--;
            }
        }
    }
}

static void terminal(int signo)
{
    putchar('\n');
    if(fg_pid)
    {
        // if there is a foreground process, propagate the signal to it
        kill(fg_pid, signo);
    }
    else
    {
        // otherwise, exit
        exit(0);
    }
}
