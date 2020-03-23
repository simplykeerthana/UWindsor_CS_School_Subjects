/*
     Name: Keerthana Madhavan
     Program: quicksort and insertion sort time complexity
     Date: 7/4/19
 */

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <limits.h>

#define CLOCK_PER_SEC 1000000

//#define MAX_SIZE 5

int listSize = 0;

//prototypes

/*
     Fucntion: Insertion sort, sorting elements in ascending order
     Input: array list, and size of the array
     output: the sorted array
 */

int insertionSort(int array[listSize], int n);

/*
     Function: Quicksorting, sorting the elements in ascending order
     Input: list, start index (low), high index values
     output: none
 */
void quicksort(int list[listSize], int p, int q);

/*
     Function: partioning the list into the left and right lists.
     Input: list, left low index, and right high index
     output: the left index value
 */

int partition(int list[listSize], int p, int q);

/*
     Function: printing the array list
     Input: array list and list size
     Output: prints the list to the screen, and return nothing
 */

void printArray(int array[listSize], int listSize);
/*
     Function: swapping to element values
     Input: low element, high element
     Output: swapped elements
 */
void swapElements(int* p, int* q);

//main fucniton
int main(void)
{
    srand(time(NULL)); //for different and unique random number generator.
    clock_t start, end;
    double elapsedTime;
    //taking the list size from the user
        printf("Enter the size of your list:  ");
        scanf("%d", &listSize);
   
    int arrayList[listSize], arrayList1[listSize];
    
   // int *arrayList = (int*)calloc(listSize, sizeof(int));
    //filling in the array with random numbers
        for(int i = 0; i<listSize; i++)
        {
            arrayList[i] = 1+  rand() % 99; // generates random number from 1 to 100
            arrayList1[i] = 1 + rand() % 99;
        }
    
    //Sorting using the Insertion Sort
        printf("\n The unsorted list is: ");
        printArray(arrayList, listSize);
    // calls the insertion sort
        if(listSize >2)
        {
            start = clock();
            printf(" Start Time: %lu\n", start);
            insertionSort(arrayList, listSize);
            end = clock();
            
        }
        else
        {
            printf("Enter the size of your list greater than 2:  ");
            scanf("%d", &listSize);
        }
    
        printf("The INSERTION sorted list is: ");
        printArray(arrayList, listSize);
        printf(" End time: %lu\n", end);
        elapsedTime = (end-start)/ CLOCK_PER_SEC;
        printf(" Elapsed (or running) Time: %f\n", elapsedTime);
    
    //Sorting using the quick sort,
        printf("\n The unsorted list is: ");
        printArray(arrayList1, listSize);
        start = clock();
        printf(" Start Time: %lu", start);
        quicksort(arrayList1, 0 , listSize - 1);
        end = clock();
        printf("\n The  QUICK sorted list: ");
        printArray(arrayList1, listSize);
        printf(" End time: %lu\n", end);
        elapsedTime = (end-start)/ CLOCK_PER_SEC;
        printf(" Elapsed (or running) Time: %f\n", elapsedTime);
    
    return 0;
    
}


//printing array template

void printArray(int array[listSize], int listSize)
{
    for (int i =0; i<listSize; i++)
    {
        printf("%d, ", array[i]);
    }
    printf("\n");
    
}

//insertion sort to sort the list in ascending order.

int insertionSort(int array[listSize],int  n)
{
    int i = 1, temp, j;
    
      while(i<=n)
      {
           temp = array[i];
           j = i;
             while(j > 0 && array[j-1] > temp)
             {
                 array[j] = array[j-1];
                 j = j-1;
             }
    
           array[j] = temp;
           i = i+1;
      }

    return *array;
}

//quick sort to sort the function
void quicksort(int list[listSize], int p, int q)
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

// quick sort is faster compared to the insertion sort in terms of running time.
