/*
    Name: Keerthana Madhavan
    Date: 2/13/2020
    Course: 3300 Operating Systems, University of Windsor
    Program: Assignment 4 part 3, This is a modification of part 1, that takes an address-space, page size, and an address as an input address. This program also checks for powers of 2 for v and s, and if s is less than v, and whether v = 16, 32, 0r 64. Finally if s is between 514 bytes and 1gb .
 
 */


#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

#define ONE_KB 1024

int main(int argc, char *argv[])
{
int pageNumber, offset, value=1, count=0,i=0;
unsigned int address=0, addrSpace=0, pageSize=0, pageOffsetBit=0;

//check the number of command line arguments
if(argc != 4)
{
printf("%s <address-space> <page size> <address>\n", argv[0]);
return -1;
}

//read and store address space, page size and adress values from the command line arguments
addrSpace = (unsigned int)atoi(argv[1]);
pageSize = (unsigned int)atoi(argv[2]);
address = (unsigned int)atoi(argv[3]);


//validate the read inputs from user
if ( addrSpace <= 0 || pageSize <= 0 || address <= 0)
{
printf("Invalid values for arguments, input right values for command line arguments.\n");
return -1;
}

// check the address space and page size are in power of 2's
if ( !((addrSpace != 0) && ((addrSpace & (addrSpace- 1)) == 0)) )
{
printf("Address space value is not a power of two.\n");
return -1;
}

if ( !((pageSize != 0) && ((pageSize & (pageSize- 1)) == 0)) )
{
printf("Page size value is not a power of two.\n");
return -1;
}

// multiple the page size with 1 kilo bytes of 1024 bytes
pageSize = pageSize * ONE_KB;

// use while loop to get the power of twos of page size, checking for, if power of 2 or not
// 4096 is , 2 power 12. Hence count is 12 here
while (value != pageSize)
{
    value=2*value;
    count++;
}

printf("The address %d contains:\n", address);

// left shift to set the least count number of bits to fetch offset with a for loop
for (i=0; i<count; i++)
{
int init = 1;
init = init << i;
pageOffsetBit = pageOffsetBit | init;
}

//right shift of count, address gives the page number
pageNumber = address >> count;
//now address and pageOffsetBit gives, offset value
offset = address & pageOffsetBit;
    
//outputs the program as instructed

printf("Page Number = %d\n", pageNumber);
printf("Offset = %d\n", offset);

return 0;

}


