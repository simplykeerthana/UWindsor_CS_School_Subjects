#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

int main()
{
    
    // pid - fork(), ths
    int pid = fork();
    
    if(pid < 0)
    {
        printf("Could not fork the process");
        return -1;
    }
    
    if(pid > 0)
    {
        printf("Paernt pid: %d \n", getpid()); // gets the pid of the parent process
        sleep(10);
    }
    else // if there is no paernt process get the childs
    {
        printf("Child Pid: %d \n", getpid());
        exit(0);
        
    }
    
    
    
    
    return 0;
}
