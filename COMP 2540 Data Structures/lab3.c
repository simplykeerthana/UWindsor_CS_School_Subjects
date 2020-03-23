/*
     Name: Keerthana Madhavan
     Program: lab 3, implementing stacks with array for Balanced Parentheses algorithm
     Date: 29/5/2019
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define MAX 50

bool isParenthesisBalanced(char expression[]);
void pushParentheses(char characterSymbol);
char popParentheses();
void checkExpression(char expression[]);

int topOfStack = -1;
int stack[MAX];
int numOpenParentheses = 0;
int numCloseParentheses = 0;

int main(void)
{
    char expression[MAX];
    // try using command line arguments as professor said.
    

    
    printf("Enter the expression \n");
    scanf("%s", expression);
    //case 1: balanced expression or not
        checkExpression(expression);
    //case 2: more open or close parenthesis
        if(numOpenParentheses > numCloseParentheses)
        {
            puts("There are more opening parentheses");
        }
        else if(numCloseParentheses > numOpenParentheses)
        {
            puts("There are more closing parentheses");
        }
    // case 3: equal parenthesis, may or may not be balanced
        else if(numOpenParentheses == numCloseParentheses)
        {
            puts("There parentheses are equal \n");
        }
    
    return 0;
}

void pushParentheses(char characterSymbol)
{
    if(topOfStack >= (MAX - 1))
    {
        puts("The stack is Full");
    }
    ++topOfStack;
    stack[topOfStack] = characterSymbol; // pushes the opening parentheses into the stack
}

char popParentheses()
{
    if(topOfStack == -1)
    {
        puts("Stack is Empty\n");
        
    }
    return(stack[topOfStack--]); //pops the closing parentheses of the stack
    
}

bool isParenthesisBalanced(char expression[])
{
    numCloseParentheses = 0;
    numOpenParentheses = 0;
    char length = strlen(expression); // holds the length of the expression
    
    for(int i = 0; i < length; i++)
    {
        
        if(expression[0] == ')') // special case when the expression starts with a closing parentheses
        {
            numCloseParentheses++;
            return false; // assuming not balanced, since there is no opening bracket in the left-most.
        }
        if(expression[i] == '(') //if character is opening parentheses, traverses from position 0 to length
        {
            pushParentheses(expression[i]); // push it to the stack
            numOpenParentheses++;
        }
        else if(expression[i] == ')') //if character is closing parentheses, traverses from position 0 to length
        {
                numCloseParentheses++; // counts the num closing bracket.
        }
    }
    
    for(int i = 0; i < numCloseParentheses; i++)
    {
        popParentheses(); // from the top of the stack pops the num close parentheses that exists
    }

        if(topOfStack == -1 && (numOpenParentheses == numCloseParentheses))
        {
            return true; // if the stack is empy and the parentheses are equal in number return true
        }
        else
        {
            return false; // not equal or no empty stack.
        }
            
            
    }

void checkExpression(char expression[])
{ //this fucntion checks the expression for balanced or not by calling the isParenthesisbalanced Function
    bool checkExpression;
    
    checkExpression = isParenthesisBalanced(expression);
    
     if(checkExpression == true)
     {
     puts("Balanced Parentheses");
     printf("The number of open %d and close parantheses %d\n", numOpenParentheses, numCloseParentheses);
     
     }
     else if(checkExpression == false)
     {
     puts("not Balanced");
     printf("The number of open %d and close parantheses %d\n", numOpenParentheses, numCloseParentheses);
     }
}

