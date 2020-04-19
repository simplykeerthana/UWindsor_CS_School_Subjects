#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main()
{
    system("./Assignment2a &"); // runs the object file of the assignment 2a
    system("ps -l"); // lists the process
    sleep(3); //sleepd for 3 seconds
    system("kill -9 $(ps -l|grep -w Z|tr -s ' ' | cut -d ' ' -f 5)");
    sleep(7);
    printf("\n\n Updated list of processs with their states \n\n");
    system("ps -l");
    
    return 0;
}
