/*
	Name: Keerthana Madhavan
	Program: Lab 1
	Date: 16/5/19
*/


#include <stdio.h>
#include <time.h> 
#include <stdlib.h>
#include <math.h>



int main(void)
{
	
	srand(time(NULL)); // to generate psuedo random numbers

	int m,n,u, v,u1,v1,  m1, n1;

	int temp1, temp2, r, extendGCD, complexity = 0, numDivisions = 0;


	for(int i = 1; i <= 50; i++) //for loop for 50 trials
	{
	         m = rand() % 100; //assigns a ranadom number to m
		 n = rand() % 100;// and n respectively
		
		numDivisions = 0;
		
		u1 = 1, 
		v1 = 0;
	
		u = 0;
		v = 1;
	
		m1 = m; 
		n1 = n;
		
	// calculates the greatest common divisor 
		while(m1 % n1 != 0)
		{
			temp1 = u;
			temp2 = v;
			u = ( u1 - (m1/n1) * u);
			v = ( v1 - (m1/n1) * v);
			numDivisions++;
			u1 = temp1;
			v1 = temp2;
				
		
			r = m1 % n1;
			m1 = n1;
			n1 = r;		
		}

		
		extendGCD = ((u*m) + (v*n)); //formula to calculates the extended GCD
	
		complexity = (2*ceil(log(n)) + 1); // calculates the complexity of the gcd

		//outputs the following fields to the screen  

		printf("Trial = %d  \tm = %d  \tn = %d  \tum + vn = %d \tn = %d \tdivisons = %d  \tcomplexity = %d \n",i, m, n, extendGCD, n1, numDivisions, complexity);
	}   

	return 0;
}
