/*
    Name: Keerthana Madhavan
    Program: COMP 3300 Assignment 1 Program 2
    Date: 07/02/2020
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <pthread.h>
#include <omp.h>

//#define NUM_OF_POINTS 1000000, 100, 1000, 10000, 100000, 1000000
#define SIZE 5

//int NUM_OF_POINTS[5] = { 100,1000,10000,100000,1000000};

int omp_get_num_procs(void);

int  num_of_point_calc(int NUM_OF_POINTS);

int circle_count = 0; // points in the circle

/* Generates a double precision random number */
double random_double()
{
    return (random() / ((double)RAND_MAX + 1)); // generates a double precision random number

}

int main(int argc, const char *argv[])
{
    
	int num_points = 0;    
 
    printf("Enter the number of points: ");
    scanf("%d", &num_points);
    printf("OpenMP separate thread simulation for num points %d\n", num_points);
    num_of_point_calc(num_points);
    
    

    return 0;
}


int  num_of_point_calc(int NUM_OF_POINTS)
{
    
    /* seed the random number generator*/
    double PI;
    srandom((unsigned)time(NULL));
    clock_t begin = clock();
    #pragma omp parallel
    {
        int i, hit_count = 0;
        double x,y;
       
        for(i = 0; i < NUM_OF_POINTS; i++)
        {
            /* generate random numbers between -1.0 and +1.0
                to obtain a random (x,y) point*/
            
            x = random_double() * 2.0 - 1.0;
            y = random_double() * 2.0 - 1.0;
            
            if(sqrt(x*x + y*y)< 1.0)
               {
                   ++hit_count;
               }
        }
        
        circle_count += hit_count;
        
        
    }
    
    // estimate the PI value
    
    PI = (4.0 * circle_count )/(NUM_OF_POINTS * omp_get_num_procs());
    clock_t end = clock();
    double time_spent = (double) (end - begin) / CLOCKS_PER_SEC;
    printf("Number of Points = %d\n", NUM_OF_POINTS);
    printf("PI = %f\n", PI);
    printf("time = %f\n", time_spent/256 );

}

