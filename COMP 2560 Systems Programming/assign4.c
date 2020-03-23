#include <stdio.h>
#include <stdlib.h>
#define MAX_DEPTH 5
int depth = 0;

void f(int size);

unsigned long sp(void);
int main(void)
{
    f(MAX_DEPTH);
}


unsigned long sp(void)
{
    int value;
   __asm__("movq %rsp, %rax");
    return value;
}




void f(int size)
{    //int localVar;
  //  unsigned long stack_ptr =sp();
    //char* pointer = (char*) malloc(500 *  (sizeof(char))); // of fivehundred bytes
 //   char* array = (char*) malloc(500 * (sizeof(char)));
  // printf("local variable address: %016ld\n", (long)&localVar);
    
   // unsigned long stack_ptr = sp();
 //    printf("local variable address: %016ld\n", (long)&localVar);
  
   // printf("address of the depth variable : %016lx\n", (long)&depth);
 //  printf("Stack pointer val before: %016lx\n", (long)&stack_ptr);
    printf("Stack pointer val befroe: %016lx\n", sp());
   // printf("value of the pointer malloc : %016lx \t array pointer: %016lx\n", (long)&pointer, (long)&array);
    
    //printf("The size %lu\n", sizeof(&pointer));
    //printf("The size of char is %lu", sizeof(char));
   
    //printf("Value of the array malloc pointer: %016ld\n", (long)&array);
     //printf("The size of array %lu\n", sizeof(&array));
   if(depth == 0)
   {
       if(size > 0)
       {
           //printf("%d\n", size);
                f(size - 1);
           // free(pointer);
           //free(array);
           // printf("Stack pointer val after: %016lx\n", sp());
       }
    }
    
   // printf("\n");
   // printf("stack pointer val after: %016lx\n", (long)&stack_ptr);
    
     // printf("local variable address: %016ld\n", (long)&localVar);
     // printf("local variable address: %016ld\n", (long)&localVar)
     \ printf("Stack pointer val after: %016lx\n", sp());
}

