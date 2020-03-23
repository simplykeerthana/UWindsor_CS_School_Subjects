
/*
 Name: Keerthana Madhavan
 Program: Lab 4, implementing fibonacci sequence for nth fibo number with three different functions
 Date: 6/06/19
 */

#include <stdio.h>
#include <time.h>
#include <limits.h>

#define MAX 10
#define CLOCK_PER_SEC 1000000
int knownF[MAX];


unsigned long long recursiveFib(int termNumber);
/*
  Function: this calculates the recursive Fibonacci of the nth term and returns unsigned long long value
  Input: a positive integer
 */

unsigned long long  iterativeFib(int termNumber);
/*
  Function: this fucniton calculates the iterative fibonacci of the nth term and returns
       unsigned long long value
  Input: a positive integer
 
 
 */

unsigned long long  dynamicFibonacci(int termNumber);
/*
     Function: this function calculates the dynamic version of the fibonacci sequence of the nth term and returns the unsigned long long value
     Input: a positive integer
 */

int main(void)
{
    clock_t start, end; // hold the clock time in the respective variable name
    double elapsedTime;
    int n;
    printf("Enter the number term to caluculate fibonacci sequence: ");
    scanf("%d", &n);
    
    printf("%d\n", CLOCKS_PER_SEC);
    
    printf("\n");
    start = clock();
    printf("Start Time : %lu\n", start); // start time before the function is called
    printf("Iterative Fibonnaci %llu\n", iterativeFib(n));
    end = clock(); // end time after the function execution is done and returned
    elapsedTime = (end-start)/ CLOCKS_PER_SEC; // calculates the elapsed time
    printf("End time: %lu\n", end);
    printf("Elapsed Time: %f", elapsedTime);
    //elapsedTime = 0;
    
    start = clock();
    printf("\n");
    printf("Start Time : %lu\n", start);
    printf("Dynamic Fibonnaci %llu\n", dynamicFibonacci(n));
    end = clock();
    elapsedTime = (end-start)/ CLOCKS_PER_SEC;
    printf("End time: %lu\n", end);
    printf("Elapsed Time: %f", elapsedTime);
    
    printf("\n");
    
    start = clock();
    printf("Start Time : %lu\n", start);
    printf("Recursive Fibonnaci %llu\n", recursiveFib(n));
    end = clock();
    elapsedTime = (end-start)/ CLOCKS_PER_SEC;
    printf("End time: %lu\n", end);
    printf("Elapsed Time: %f", elapsedTime);
  //  elapsedTime = 0;

    return 0;
}


unsigned long long recursiveFib(int termNumber)
{
    if(termNumber == 0)
    {
        return 0; // if the fzero term number is zero
    }
    else if(termNumber == 1)
    {
        return 1; //if the fone term number is one
    }
    
    return recursiveFib(termNumber - 1) + recursiveFib(termNumber -2); // recursive statement
}

unsigned long long iterativeFib(int termNumber)
{
   unsigned long long previousTerm = 1, previousPreTerm = 0, result = 0;
    
    if(termNumber == 0)
    {
        return 0; // if the fzero term is zero
    }
    if(termNumber == 1)
    {
        return 1; // if the fone term is one
    }
    
    for(int i = 2; i <= termNumber; i++)
    {
        result = previousTerm + previousPreTerm;
        previousPreTerm = previousTerm;
        previousTerm = result;
    }
  
        return result; // returns the final value
}


unsigned long long dynamicFibonacci(int termNumber)
{
    if(knownF[termNumber] != 0)
    {
        return knownF[termNumber];
    }
    
    int t = termNumber;
    
    if(termNumber < 0)
    {
        return 0; // if non negative number
    }
    
    if(termNumber > 1)
       {
           t = dynamicFibonacci(termNumber - 1) + dynamicFibonacci(termNumber -2);
       }
    
    return knownF[termNumber] = t;
}
