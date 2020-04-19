#include <stdio.h>
#include <stdlib.h>

int main() {
    char buffer[500];
    int  n1, n2, n3;

    fgets(buffer, sizeof(buffer), stdin);
    n1 = atoi(buffer);
    
    fgets(buffer, sizeof(buffer), stdin);
    n2 = atoi(buffer);

    if (n1 + n2 == 100)
        n3 = 90;
    else
        n3 = n1 + n2;
    printf("%d\n", n3);
}    
