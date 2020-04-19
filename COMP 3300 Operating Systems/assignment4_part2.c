/*
   Name: Keerthana Madhavan
   Date: 02/13/2020
   Course: 3300 Operating Systems, University of Windsor
   Program: Assignment 4 Part 2, this program generates the random virtual address between 0 and 2^32-1 and compute the page number and offset for each address. So for all the random virtual address,the program outputs teh CPU time of
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define n 1000000 // random addresses
#define MAX_VIRTUAL_ADDRESS 429967295 // (2^32-1)
#define PAGE_NUMBER 0xFFFFF000
#define OFFSET 0x00000FFF

// to generate a random double precision numbers)

double random_double()
{
    
    return random() / ((double)RAND_MAX + 1);
}

int main(int argc, char *argv[])
{
    unsigned long pageNumber, offset, address;
    
    srandom((unsigned)time(NULL));
    clock_t begin = clock();
    
    for(address = 0; address < n; address++)
    {
        //generate the random address
        
        address = random_double() * n;
        pageNumber = (address & PAGE_NUMBER) >> 12; // using the right shift operator
        offset = address & OFFSET;
        
    }
    
    clock_t end = clock();
    
    //outputs to the screen as instructed
    double time_spent = (double) (end-begin) /CLOCKS_PER_SEC;
    printf("total CPU time: %f\n", time_spent);
    
    
    return 0;
}

