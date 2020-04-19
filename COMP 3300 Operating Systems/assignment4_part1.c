/*
    Name: Keerthana Madhavan
    Date: 02/13/2020
    Course: 3300 Operating Systems, University of Windsor
    Program: Assignment 4 Part 1. A program that takes a decimal virtual address and outputs the page number and offset for the given address.
 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define PAGE_NUM 0xFFFFF000
//#define OFFSET 0x00000FFF

int main (int argc, char *argv[])
{
    unsigned long pageNumber, offset, address;
    address = atoll(argv[1]); //gets the command line argument
    
    if(argc != 2)
    {
        fprintf(stderr, "Please enter: ./assignment4_part1.c followed by the virtual address in decimal\n");
        return -1;
    }
    
    // mask the page number value 
    pageNumber = address >> 12; // right shifts operator
    offset = address & 0xfff;
    
    // prints the output as instructed
    
    printf("The address %lu contains: \n", address);
    printf("Page Number = %lu\n", pageNumber);
    printf("offset = %lu\n", offset);
    
    
    return 0;
}
