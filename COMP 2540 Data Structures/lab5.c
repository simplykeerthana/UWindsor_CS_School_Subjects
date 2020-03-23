/*
     Name: Keerthana Madhavan
     Program: Lab 5 Maximum Contigousous Subsequence,recursive and dynamic programming.
     Date: 13/6/19
 */

#include <stdio.h>

struct maxContingous MaxSubSeqDivideNConquer(int seq[], int leftV, int rightV, int limitV);

struct maxContingous
{
    int sum;
    int leftV;
    int rightV;
};

// using the but recursively using the same approach as lab 2 but recursively.
int main()
{
    //testing different sequences
    
    int seq[] = {-1, -2, 3,4,5, -6};
    int seq1[] = {1, 2, 3, -5, -6, 0};
    int seq2[] = {-1, -2,1, 6, 7, 9};
    
    int limit = 6;// max size of the array limit
    
    struct maxContingous result = MaxSubSeqDivideNConquer(seq, 0, 5, limit); // result stores the struct value returned by Maximum subsequence
    
    printf("\n");
    printf("Sequence 1: {-1, -1, 8, 9,2,-6, 9, 3, 4,5} \n");
    printf("The maximum sum sequence %d starts at index %d and ends at index %d. \n", result.sum, result.leftV, result.rightV);
    
    printf("\n");
    
    struct maxContingous result1 = MaxSubSeqDivideNConquer(seq1, 0, 5, limit); // result1 stores the struct value returned by Maximum subsequence
    
    printf("Sequence 2: {1, 2,3, -5, -6, 0, 5, 6, 3, -2} \n");
    printf("The maximum sum sequence %d starts at index %d and ends at index %d \n", result1.sum, result1.leftV, result1.rightV);
    
    
    printf("\n");
    
    struct maxContingous result2 = MaxSubSeqDivideNConquer(seq2, 0, 5, limit); // result1 stores the struct value returned by Maximum subsequence
    
    printf("Sequence 3: {-1, -2, 1, 6,7,9, 3, 4, 5, -1} \n");
    printf("The maximum sum sequence %d starts at index %d and ends at index %d. \n", result2.sum, result2.leftV, result2.rightV);
    
    printf("\n");
}

struct maxContingous MaxSubSeqDivideNConquer(int seq[], int leftV, int rightV, int limitV)
{
    
    struct maxContingous result; // stores the final value of the following fucntion
    
    struct maxContingous leftSide, rightSide, straddling1, straddling2;
    
    
    if(leftV == rightV)
    {//base case
        if(seq[leftV] <0)
        {
            result.sum = 0;
            result.leftV = -1;
            result.rightV = 0;
        }
        else
        {
            result.sum = seq[leftV];
            result.leftV = leftV;
            result.rightV = rightV;
        }
    }
    else
    {
        int middle = ((leftV + rightV) /2);
        
        //1. finding the maximum sequence of the left side
        
        leftSide = MaxSubSeqDivideNConquer(seq, leftV, middle, limitV);
        
        //2. finding the maximum sequence of the right side
        
        rightSide = MaxSubSeqDivideNConquer(seq, middle+1, rightV, limitV);
        
        // 3. finding the maximum sequence of the postfix form, in between left and middle\
        
        straddling1 = MaxSubSeqDivideNConquer(seq, leftV, middle, limitV);
        
       // 4. finding the maximum sequence of the prefix form, in between middle and right
        
        straddling2 = MaxSubSeqDivideNConquer(seq, middle+1, rightV, limitV);
        
        
        //compare the 3 diff combinations of the maximum sequence from left, right, prefix and postfix form
        
        
        //1. if the maximum sequence is from the left hand side
        
        if(((leftSide.sum - rightSide.sum >=0) && (leftSide.sum - (straddling1.sum + straddling2.sum)) >=0))
        {
            result.sum = leftSide.sum;
            result.leftV = leftSide.leftV;
            result.rightV = leftSide.rightV;
        }
        
        //2. if the maximum sequence is from the right hand side
        
        else if(((rightSide.sum - leftSide.sum >=0) && (rightSide.sum - (straddling1.sum + straddling2.sum)) >=0))
        {
            result.sum = rightSide.sum;
            result.leftV = rightSide.leftV;
            result.rightV = rightSide.rightV;
        }
        
        else if(((straddling1.sum + straddling2.sum) - leftSide.sum >=0) && ((straddling1.sum + straddling2.sum) - rightSide.sum >=0))
        {
            result.sum = straddling1.sum + straddling2.sum;
            result.leftV =leftSide.leftV;
            result.rightV = rightSide.rightV;
        }
        
    }
    
    return result;
    
    
}
