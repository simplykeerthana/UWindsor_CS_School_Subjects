/*
     NAME: KEERTHANA MADHAVNA
     Date: 18/07/19
     Program: heapsort vs quicksort time analysis.
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <limits.h> // for the time complexity and heapsort.

#define CLOCK_PER_SEC 1000000
#define MAX_SIZE   100
//int size = 7;

void heapify(int i, int j, int a[]);

void buildheap(int a[], int size);

void Heapsort(int a[], int size);

void swapElements(int* p, int* q);

int partition(int list[], int p, int q);
/*
 Function: printing the array list
 Input: array list and list size
 Output: prints the list to the screen, and return nothing
 */

void quicksort(int list[MAX_SIZE], int p, int q);
/*
 Function: partioning the list into the left and right lists.
 Input: list, left low index, and right high index
 output: the left index value
 */

void printArray(int array[MAX_SIZE], int listSize);
/*
 Function: swapping to element values
 Input: low element, high element
 Output: swapped elements
 */


int main(void)
{
    srand(time(NULL));
    clock_t start,end;
    double elaspedTime;
    int array[MAX_SIZE], array1[MAX_SIZE];
  // int array[MAX_SIZE] = {12, 10, 20, 19, 8, 7, 15};
    //int array1[MAX_SIZE] = {45, 34, 45, 11, 23, 1, 0};
    
    // fills in both arrays with random numbers of size 100
    for(int i = 0; i < MAX_SIZE; i++)
    {
        array[i] = 1 + rand() % 99; // generates random number between 1 to 100
        array1[i] = 1 + rand() % 99;
    }
    
    //buildheap(array);
    
    //Heapsort Testing
    printf("\n");
    printf("Before Heap Sorting: ");
    printArray(array, MAX_SIZE);
    start = clock();
    printf("\tStart Time of Heap Sort: %lu\n", start);
    Heapsort(array, MAX_SIZE);
    end = clock();
    printf("\tEnd time of HeapSort: %lu\n", end);
    printf("After Heap Sorting: ");
    printArray(array, MAX_SIZE);
    elaspedTime = (end-start)/ CLOCK_PER_SEC;
    printf("Elapsed (or running) Time of Heap Sort is: %f\n", elaspedTime);
    printf("\n");
    
    //quicksort testing
    printf("Before Quick Sorting: ");
    printArray(array1, MAX_SIZE);
    start = clock();
    printf("\tStart Time of quickSort: %lu\n", start);
    quicksort(array1, 0, MAX_SIZE -1);
    end = clock();
     printf("\tEnd time of Quicksort: %lu\n", end);
    printf("After Quick Sorting: ");
    printArray(array1, MAX_SIZE);
    elaspedTime = (end-start)/CLOCK_PER_SEC;
    printf(" Elapsed (or running) Time of Quick Sort is: %f\n", elaspedTime);
    printf("\n Based on the start and end times of both sorting techniques, Quicksort is way faster\ncompaered to heapsort\n");
    return 0;
    
}


void Heapsort(int a[], int size)
{
    buildheap(a, size);
    
    int i = size - 1, temp;
    
    while(i >= 1)
    {
        temp = a[0];
        a[0] = a[i];
        a[i] = temp;
        
        heapify(0, i-1, a);
        i = i -1;
    }
    
    return;
}

void buildheap(int a[], int size)
{
    int i = size -1;
    
    while(i >= 0)
    {
        heapify(i, size-1, a);
        i = i-1;
    }
    
    return;
}

void heapify(int i, int j, int a[])
{
    int temp, k;
    
    if( j < (2*i) + 1)
       {
           return; // i is a leaf node
       }
       
       if(j == 2*i + 1)
       {
           k = 2*i+1;
       }
       else
        {
            if(a[2*i+1] > a[2*i+2])
                {
                    k = 2*i+1;
                }
            else
                {
                    k = 2*i+2;
                }
        }
    
       
       if(a[k]>a[i])
       {
           //exchange a[k] and a[i]
           
           temp = a[i];
           a[i] = a[k];
           a[k] = temp;
           
           heapify(k,j,a); // you continue the heapification
           
       }
    
    return;
}


// quick sort algorithm from lab 7

    //quick sort to sort the function
    void quicksort(int list[MAX_SIZE], int p, int q)
    {
        int r;
        
        if(p < q)
        {
            r = partition(list, p, q);
            quicksort(list,p,r -1);
            quicksort(list, r + 1, q);
            
        }
    }

    int partition(int list[], int p, int q)
    {
        int a, lp;
        
        a = list[q];
        lp = p - 1;
        
        
        for(int j = p; j <= q -1; j++)
        {
            if(list[j] <= a)
            {
                lp++;
                swapElements(&list[lp], &list[j]);
            }
        }
        
        swapElements(&list[lp + 1], &list[q]);
        
        return (lp + 1);
        
    }

    void swapElements(int* p, int* q)
    {
        int temp = *p;
        *p = *q;
        *q = temp;
    }

void printArray(int array[MAX_SIZE], int listSize)
{
    for (int i =0; i<listSize; i++)
    {
        printf("%d, ", array[i]);
    }
    printf("\n");
    
}
