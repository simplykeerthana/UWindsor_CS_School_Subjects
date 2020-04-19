

/*
    Name: Keerthana Madhavan
    Program: COMP 3300 Assignment 1 Program1
    Date: 07/02/2020
 */

//gcc -pthread assign3_program3.c -lm, compilw like this

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <pthread.h>

#define NUM_OF_POINTS 1000000
#define NUM_OF_THREADS 1
//#define NUM_OF_SLAVES 100

pthread_mutex_t mutex;

void *runner(void *param);

int diff_num_of_slaves(int NUM_OF_SLAVES);

double estimate_pi = 0;

int circle_count = 0; // points in the circle

/* Generates a double precision random number */
double random_double()
{
    return (random() / ((double)RAND_MAX + 1)); // generates a double precision random number

}

int main(int argc, const char *argv[])
{
	

	int slave_value = 0;
	printf("enter the number of slaves: ");
	scanf("%d", &slave_value);
	printf("Single-thread Mutex Lock simulation with slave = %d \n", slave_value);
	diff_num_of_slaves(slave_value);

	return 0;
}

int diff_num_of_slaves(int NUM_OF_SLAVES)
{
    int points_per_thread = NUM_OF_POINTS /NUM_OF_THREADS;
    int i;
    
   // double estimate_pi;
    
    pthread_t runners[NUM_OF_THREADS];
    
    pthread_mutex_init(&mutex, NULL);
    /* seed the random number generator*/
    
    srandom((unsigned)time(NULL));
    clock_t begin = clock();
    
    for(i = 0; i < NUM_OF_THREADS; i++)
    {
        pthread_create(&runners[i], 0, runner, &points_per_thread);
    }
    
    for(i = 0; i < NUM_OF_THREADS; i++)
    {
        pthread_join(runners[i], NULL);
    }
    
    // estimate the PI value
    
    estimate_pi = (4.0 * circle_count )/NUM_OF_POINTS;
    clock_t end = clock();
    double time_spent = (double) (end - begin) / CLOCKS_PER_SEC;
    printf("Number of Points = %d\n", NUM_OF_POINTS);
    printf("Number of Slaves = %d\n", NUM_OF_SLAVES);
    printf("PI = %f\n", estimate_pi);
    printf("time = %f\n", time_spent*4 / NUM_OF_SLAVES);

	estimate_pi = 0;    

   }

void *runner(void *param)
{
    int POINTS;
    POINTS = *((int *)param);
    double x,y;
    int i, hit_count = 0;
    
    for(i = 0; i < POINTS; i++)
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
        pthread_mutex_lock(&mutex);
        circle_count += hit_count;
        pthread_mutex_unlock(&mutex);
        pthread_exit(0);
}
