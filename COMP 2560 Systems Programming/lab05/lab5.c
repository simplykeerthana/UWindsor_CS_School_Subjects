#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/wait.h>
#include <signal.h>

enum { R_OKOK = 0, R_EXIT = -1, R_ERROR = -2 };
int status;
pid_t pid;


// part one


int prompt(const char * ps, int (*handler)(const char *));
int handler(const char * str);

void sig_intHandler(int);
pid_t setpgrp(void);

int main(int argc, char * argv[])
{
    status = 0;
    
    if(isatty(fileno(stdin)))
    {
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
    //signal(SIGINT, SIG_IGN);
    signal(SIGINT, sig_intHandler);
    // no-ops
    if(str[0] == '\0' || str[0] == '\n' || str[0] == '#')
    {
        return R_OKOK;
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
    else if(!strcmp(str, "^C"))
    {
        return R_EXIT;
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
    
    // fork
   // pid_t pid = fork();
    pid_t cpid;
    pid = fork();
    
    if(!strcmp(*(next-1),"&"))
    {
        printf("%d\n", pid);
       return R_OKOK;
        
    }
    
   // *(next-1) = NULL;
    
   
    
    if(pid > 0)
    {
       
        // parent - wait and store status
        setpgid(cpid, cpid);
        wait(&status);
      
        return R_OKOK;
    }
    else if(pid == 0)
    {
       
        
      //  setgrp();
        

        // child - exec the command
       
        if(execvp(args[0], args) == -1)
        {
            fprintf(stderr, "error: %s\n", strerror(errno));
            free(tstr);
            exit(1);
        }
        
        
    }
    
    
    
}

void sig_intHandler(int signo)
{
    signal(signo, SIG_IGN);
}
