//COMP_3300 Operating Systems Assignment 1
// Keerthana Madhavan
// Student No: 104995097


#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h> //for file descriptor
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>




extern int errno;


int main(int argc, char *argv[])
{
   
    int fd, fd1; // file descriptors for
    char inputFile, outputFile;
  
    char c[1]; //  buff size to read one byte at a time
    int file_size;
    int errnum;
    
    char inError[] = "Error: Input file doesn't exist";
    char outError[] = "Error: Output file already exists";
  
    // write() functions used to read in input and output file names
    char endNormally[] = "Process Copy Complete: Terminating Normally\n";
    char inputPrompt[] = "Input File: ";
    write(STDOUT_FILENO, inputPrompt, sizeof(inputPrompt)/sizeof(char));
    char buf1[128];
    int inputCharacters = read(0, buf1, sizeof(buf1)/sizeof(char)-1); // you read from stdin
    buf1[inputCharacters-1] = '\0'; // null terminate the input file
    char outputPrompt[] = "Output File: ";

    write(STDOUT_FILENO, outputPrompt, sizeof(outputPrompt)/sizeof(char));
    char buf2[128];
    int outputCharacters = read(0, buf2, sizeof(buf2)/sizeof(char)-1); // read the output file from the stdin
    buf2[outputCharacters - 1] = '\0';

   
    //set up file descriptors for input file
    if( ( fd = open(buf1, O_RDONLY)) < 0)
    {
        write(STDOUT_FILENO, inError, sizeof(inError)/sizeof(char));
        return -1;
    }
    

    
    //get the output file name, through the terminal
    
    if((fd1 = open(buf2, O_CREAT | O_EXCL | O_WRONLY , 0777)) < 0)
    {
        write(STDOUT_FILENO, outError, sizeof(outError)/sizeof(char));
        return -1;
    }
    
   // this is the part where all the contents from input file will be copied to the output file
     
        while( read(fd, c, 1)>0)
            {
               // write(1, &buf, file_size);
                    write(fd1, c, 1);
                //  printError(strerror(errno));
            }
        

    close(fd); // close the input file
    
    close(fd1); // close the outfile
    
    printf("all the contents have been copied");
    
    return 0;
}

