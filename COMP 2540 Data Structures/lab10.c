/*
     Name: Keerthana Madhavan
     Date: 23/05/18
     Program: Comparing linear probing with theoretical theorms, hash tables.
 */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define RANDNUM 10000

int checkPrime(int n)
{
    //prime
    if(n==2 || n==3)
    {
        return 1;
    }
   // not prime
    if(n==1 || n%2 ==0)
    {
        return 0;
    }
    
    for(int i=3; i*i <= n; i += 2)
    {
        if(n%i==0)
        {
            //if the element has a factor that is less than n, it is not prime
            return 0;
        }
    }
    
    return 1; // if prime
}


int checkNextPrime(int n) // checks and returns the next prime integer
{
    if(n%2 == 0) // not prime, so increment the n value
    {
        n++;
    }
    
     //checks if the following elements at +2 intervals are prime or not
    for(; !checkPrime(n); n +=2);
    
    return n; // if the prime number is found
}

double probeHash(int hashTable[], int np, char option, int rnum)
{
    double numProbes =0; // total of all probes
    int i = 0;
    int hash = (rnum + i) % np; // indexes
    numProbes++;
    
    if(option == 'L' || option == 'l') // for linear probe
    {
        while(!(hashTable[hash] == 0)) // if not empty
        {
            hash = (rnum + i) % np; // finding the next index
            i++;
            numProbes++;
        }
    }
    else if(option == 'Q' || option == 'q') //for quadratic
    {
        while(!(hashTable[hash] == 0))
        {
            hash = (rnum + i*i) % np; // finding the nexrt index
            i++;
            numProbes++;
        }
    }
    
    if(hashTable[hash] == 0)
    {
        hashTable[hash] = rnum; // insert random number into the empty place
    }
    
    return numProbes;
}

double populateHash(int hashTable[], int np, char option)
{
    double numProbes = 0;
    
    for(int i = 0; i < RANDNUM; i++) //insert 10000 randon inputs
    {
        int rnum = rand();
        
        numProbes += probeHash(hashTable, np, option, rnum);
    }
    
    return (numProbes)/(RANDNUM); // avg num of probes per random num
}

int main(void)
{
    float loadFactor;
    srand(time(NULL)); //unique rand generator
    
    puts(" Please enter the load factor between 0.1 and 0.9:");
    scanf("%f", &loadFactor);
    
    while(!(loadFactor <= 0.9 && loadFactor >= 0.1 )) // if in the range
    {
        puts("Not valid load factor. Try again!");
        scanf("%f", &loadFactor);
    }
    
    printf("\n Enter the probing option L or Q: ");
    char option;
    scanf("%c", &option);
    while(!(option == 'L' || option == 'Q'))
    {
           puts(" not valid option, Try again!");
           scanf("%c", &option);
    }
           
           int size = (int) 10000/ loadFactor; //size of the hash table from the instructions
           
           int np = checkNextPrime(size); // num  od probes
           
           int* hashTable = (int*)calloc(np, sizeof(int));
           
           double numProbes = populateHash(hashTable, np, option);
           
           if(option == 'L')
           {
               float expectedprobes = (1 + 1/(1 -loadFactor)) /2;
               
              printf("%.3f is the average number of probes  from linear probing, but the expected average number of probes using linear probing from theorem  is %.3f.\n", numProbes, expectedprobes);
           }
    
            if(option =='Q' )
            {
                  printf("%.3f On the other hand is the average number of probes from quadratic probing is \n", numProbes);
            }
           
           
    free(hashTable); // free calloc
    return 0;
}
