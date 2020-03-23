/*
     Name: Keerthana Madhavan
     Date: 27/06/19
     Program: Coin Change, C dyanmic program
 
 */

#include <stdio.h>
#include <limits.h>
#define MAX_SIZE 50
int coinChange(int changeAmount, int numOfDemnominations, int valueOfDenomination[MAX_SIZE]);

int main(void)
{
    
    int k = 0, numOfDemnominations = 4;
    int valueOfDenomination[] = {1, 5, 10, 25};
    int result;
    printf("Enter the amount you need change for: ");
    scanf("%d", &k);
    result = coinChange(k, numOfDemnominations, valueOfDenomination);
    printf("%d", result);
}

int coinChange(int changeAmount, int numOfDemnominations, int valueOfDenomination[MAX_SIZE])
{
    int minCoinsUsed[MAX_SIZE] = {0};
    int lastCoinUsed[MAX_SIZE] = {0};
    int minCoins, lastCoin, newCoin;
    int cents = 1;
    
    int j;
    
    while(cents < changeAmount)
    {
        minCoins = cents;
        newCoin = 1;
        j = 1;
        
        while( j < numOfDemnominations)
        {
            if(valueOfDenomination[j] <= cents)
            {
               if( minCoinsUsed[cents - valueOfDenomination[j]] + 1 < minCoins)
               {
                   minCoins = minCoinsUsed[cents - valueOfDenomination[j]] + 1;
                   newCoin = valueOfDenomination[j];
               }
            }
            
            j = j + 1;
            
        }
        
        minCoinsUsed[cents] = minCoins;
        lastCoinUsed[cents] = newCoin;
        cents = cents + 1;
    }
    
    return *(minCoinsUsed, lastCoinUsed);
}

