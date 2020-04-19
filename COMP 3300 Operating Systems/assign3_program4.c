/*
    Name: Keerthana Madhavan
    Program: COMP 3300 Assignment 1 Program1
    Date: 07/02/2020
 */

// g++ -fopenmp assign3_program4.c, compile like this

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <omp.h>
#define NUM_OF_POINTS 1000000

//#define NUM_OF_SLAVES 100

int diff_num_of_slaves(int NUM_OF_SLAVES);

int circle_count = 0; // points in the circle

/* Generates a double precision random number */
double random_double()
{
    return (random() / ((double)RAND_MAX + 1)); // generates a double precision random number

}

int main(int argc, const char *argv[])
{

	int slave_value = 0;
	
	printf("Enter the number of slaves: ");
	scanf("%d", &slave_value);
	printf("OpenMP race condition simulation with slave = %d\n", slave_value);
	diff_num_of_slaves(slave_value);




return 0; 
}  


int diff_num_of_slaves(int NUM_OF_SLAVES)
{
    int i;
    
    double PI;

    /* seed the random number generator*/
    
    srandom((unsigned)time(NULL));
    clock_t begin = clock();
   
#pragma omp parallel 
    {
        int hit_count = 0, i;
        double x,y;
        
//	#pragma omp for

            for(i = 0; i < NUM_OF_POINTS; i++)
            {
            
                /* generate random numbers between -1.0 and +1.0
                           to obtain a random (x,y) point*/
                       
                       x = random_double() * 2.0 - 1.0;
                       y  = random_double() * 2.0 - 1.0;
                       
                       /* to check is (x,y) point within the circle*/
                       if(sqrt(x*x + y*y)< 1.0)
                       {
                           ++hit_count;
                       }
                
            }

#pragma omp critical
        {
            circle_count += hit_count;
        }
    
    }
    // estimate the PI value
    
    PI = (4.0 * circle_count / (NUM_OF_POINTS * omp_get_num_procs()));
    clock_t end = clock();
    double time_spent = (double) (end - begin) / CLOCKS_PER_SEC;
    printf("Number of Points = %d\n", NUM_OF_POINTS);
    printf("Number of Slaves = %d\n", NUM_OF_SLAVES);
    printf("PI = %f\n", PI);
    printf("time = %f\n", time_spent /(1024 * NUM_OF_SLAVES));
    

   
}

