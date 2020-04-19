#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char buffer[500];
    int  n;

    fgets(buffer, sizeof(buffer), stdin);
    n = strlen(buffer) - 1;
    
    printf("%d\n", n);
}    
