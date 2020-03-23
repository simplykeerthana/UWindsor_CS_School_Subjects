#include <stdio.h>
#include <string.h>
#include <unistd.h>
#define MAX_LINE 80

#define R_OK 0
#define R_EXIT -1
#define R_ERROR -2

//enum {R_OK = 0, R_EXIT = -1, R_ERROR = -2}; //0, -1, -2

int state = 0;
int prompt(const char* ps, int (*handler)(const char*));
int handler(const char* ps);

int main(void)
{
    char *ps = "Hello User, please enter something";
   
    while(state != R_EXIT)
    {
        prompt(ps, &handler);
    }
    
    return 0;
}

int handler(const char* ps)
{
    
    
    if(strcmp(ps, "pid") ==0)
    {
        printf("pid: %d \n", getpid());
        //return R_OK;
        state = R_OK;
    
    }
    else if(strcmp(ps, "uid") ==0)
    {
        printf("uid: %d \n", getuid());
        state = R_OK;
        //return R_OK;
    }
    else if(strcmp(ps, "exit\n") == 0)
    {
       // state = R_EXIT;
        return R_EXIT;
    }
    else
    {
        printf("Error: unkown command\n *");
        state = R_ERROR;
       // return R_ERROR;
    }
    
    return state;
    
}

int prompt(const char* ps, int (*handler)(const char*))
{
    char str[MAX_LINE];
    
    printf("%s ", ps);
    
    fgets(str, MAX_LINE, stdin);
    
    
    if(str[strlen(str) -1] =='\n') // eliminates the new line at the end of the line
    {
        str[strlen(str) -1] = '\0'; //replaces it with null
    }
    
    
    return handler(str);
    
    
    
}

    
    
    

