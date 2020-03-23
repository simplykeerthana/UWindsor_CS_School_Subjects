/*
     Name: Keerthana Madhavan
     Program: Lab 2
     Student ID: 104995097
 */


#include <stdio.h>
#include <stdlib.h> // for random number genrator.
#include <time.h>
#include <math.h>

#define ASIZE 15 //the size of the Array

void maximumSubsequence(int list[],int sum[], long int start[]);

void outputTable(int list[],int sum[],long int start[], int maxmimumSq, int startIndex, int endIndex);

void resetAllValues(int sum[],long int  start[]);


int main(void)
{
    srand(time(NULL));  //for random numbers
    
  // int randNumber = rand() % 200 - 100 + 1 ; // generates a random number between 1 and 100, both positve
    
     long int start[ASIZE] = {0};
    
    for(int i=0; i<ASIZE; i++)
    {
        start[i] = -1;  //initialize all start values to -1 (i-1) because it will change only if sequence and sum are equal at that index
    }
    
    int sum[ASIZE] = {0}; // all sums indeces are set to 0
    
    // An array declaration for 10 lists
    int list1[ASIZE], list2[ASIZE], list3[ASIZE], list4[ASIZE], list5[ASIZE], list6[ASIZE], list7[ASIZE], list8[ASIZE], list9[ASIZE], list10[ASIZE];
    
    //int list[10] = {-99,94,-96,12,99,41,-4,-62,-29,50};
    
    // sets all the lists with a random number
    for(int k = 0; k < ASIZE; k++)
    {
        list1[k] = rand() % 200 - 100 + 1;
        
        list2[k] = rand() % 200 - 100 + 1;
        
        list3[k] = rand() % 200 - 100 + 1;
        
        list4[k] = rand() % 200 - 100 + 1;
        
        list5[k] = rand() % 200 - 100 + 1;
        
        list6[k] = rand() % 200 - 100 + 1;
        
        list7[k] = rand() % 200 - 100 + 1;
        
        list8[k] = rand() % 200 - 100 + 1;
        
        list9[k] = rand() % 200 - 100 + 1;
        
        list10[k] = rand() % 200 - 100 + 1;

    }
    
    // calls the function that computes the contiguous subsequence of the list of numbers
    printf("List 1 Maximum Subsequence: \n");
    maximumSubsequence(list1, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 2 Maximum Subsequence: \n");
    maximumSubsequence(list2, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 3  Maximum Subsequence: \n");
    maximumSubsequence(list3, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 4  Maximum Subsequence: \n");
    maximumSubsequence(list4, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 5  Maximum Subsequence: \n");
    maximumSubsequence(list5, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 6  Maximum Subsequence: \n");
    maximumSubsequence(list6, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 7 Maximum Subsequence: \n");
    maximumSubsequence(list7, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 8 Maximum Subsequence: \n");
    maximumSubsequence(list8, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 9  Maximum Subsequence: \n");
    maximumSubsequence(list9, sum, start);
    printf("\n");
    resetAllValues(sum, start);
    printf("List 10 Maximum Subsequence: \n");
    maximumSubsequence(list10, sum, start);
    printf("\n");
    resetAllValues(sum, start);

   
    return 0;
}


void maximumSubsequence(int list[],int sum[],long int start[])
{
    int maxmimumSq = 0; // hold the maximum number in the list
    int endIndex;
    int startIndex;
    
    for(int i=0; i< ASIZE; i++)
    {
        if(sum[i-1] + list[i] > list[i])
        {
            sum[i] = sum[i-1] +list[i];
            
            if(sum[i] < 0)
            {
                sum[i] = 0; // if there is negative sum, then the value will be set to zero
            }
            
            start[i] = start[i-1];
        }
        else if(list[i]>=0)
        {
            start[i] = i;
            sum[i] = list[i];
        }
        else;
        
        
    }
    
    for(int k = 0; k <ASIZE; k++)
    {
        if(maxmimumSq < sum[k])
        {
            
            maxmimumSq = sum[k];
            endIndex = k;
            startIndex = start[k];
        }
        
    }
    
    outputTable(list, sum, start, maxmimumSq, startIndex, endIndex);
   
}

void outputTable(int list[],int sum[],long int start[], int maxmimumSq, int startIndex, int endIndex)
{
    //make one print output function.
    printf("index\t\t");
    for(int k = 0; k<ASIZE;k++)
    {
        printf("%d\t", k);
    }
    printf("\n");
    printf("squence\t\t");
    for(int i=0; i<ASIZE; i++)
    {
        printf("%d\t", list[i]);
    }
    printf("\n");
    printf("start index\t");
    for(int i=0; i<ASIZE; i++)
    {
        printf("%ld\t", start[i]);
    }
    printf("\n");
    printf("segment sum\t");
    for(int i=0; i<ASIZE; i++)
    {
        printf("%d\t", sum[i]);
    }
    printf("\n");
    printf("\n The largest subsequence value is %d, from index %d to %d!\n", maxmimumSq, startIndex, endIndex);
    printf("\n");
    
}

void resetAllValues(int sum[],long int  start[])
{
    for(int i = 0; i<ASIZE; i++)
    {
        sum[i] = 0;
        start[i] = -1;
    }
}
