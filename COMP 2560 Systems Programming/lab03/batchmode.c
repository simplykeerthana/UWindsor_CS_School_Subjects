#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>

#define MAX_LINE 80
#define BUFFER_SIZE 80

//int R_OK = 0;
//int R_EXIT = -1;
//int R_ERROR = -2;
#define R_OK 0
#define R_EXIT -1
#define R_ERROR -2

//enum {R_OK = 0, R_EXIT = -1, R_ERROR = -2}; //0, -1, -2

int prompt(const char* ps, int (*handler)(const char*));
int handler(const char* ps);


FILE *fp;

int main(int argc, char *argv[])
{
    char *ps = "Hello User, please enter something";
   
    //FILE *fd = fopen(argv)
    char buf[BUFFER_SIZE];
    char c;
    int file_size;
   
    
     //fd = fopen(argv[1], O_RDONLY);

    fp = fopen(argv[1], "r");
    
    //dup2(fd, STDIN_FILENO);
    
    

    

    
    while(&handler)
    {
        prompt(ps, &handler);
    }
    
    
};

int handler(const char* ps)
{
  
    
    if(strcmp(ps, "pid") ==0)
    {
        printf("pid: %d \n", getpid());
        return R_OK;
    }
    else if(strcmp(ps, "uid") ==0)
    {
        printf("uid: %d \n", getuid());
        return R_OK;
    }
    else if(strcmp(ps, "exit\n") == 0)
    {
        return R_EXIT;
    }
    else
    {
        printf("Error: unkown command\n *");
        return R_ERROR;
    }
    
}

int prompt(const char* ps, int (*handler)(const char*))
{
   
    
  //  printf("%s ", ps);
 
    
    
    char *line[80];
        /*
        while(fgets(line, sizeof(line), fp) != NULL)
        {
          
            
            if(line[strlen(line) -1] =='\n') // eliminates the new line at the end of the line
            {
                line[strlen(line) -1] = '\0'; //replaces it with null
            }
           
            
        }*/
        
        while(fgets(line, sizeof(line), line))
        {
            //sscanf(line, fp, "%s");
            printf("%s\n", line);
            if(line[strlen(line) -1] =='\n') // eliminates the new line at the end of the line
            {
                line[strlen(line) -1] = '\0'; //replaces it with null
            }
            handler(line);
        }
        
    
    fclose(fp);
    
    
    //fgets(str, MAX_LINE, stdin);
   // fgets(str, MAX_LINE, stdin);
   
  
    
    return 0;
  // return handler(str);
    
    
    
}
