#define Buffer_Size 512
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>
#include <queue>
using namespace std;

void process_matrix(char * string){
	
		for(; *string; string++){
			*string = tolower(*string);
			if (*string == '\n' || *string == '\r')
				*string = 0;
		}
}

int main(int argc, char *argv[]){
	
	
	if(argc < 2){
		printf("Enter the input text files ./a.out text files \n");
		return 1;
	}

	FILE *input_file = fopen(argv[1], "r");
    if (input_file == NULL)
    {
        cout << "Error: could not open file";
        return 1;
    }
	
	char buffer[Buffer_Size];
	
	int matrix[Buffer_Size][Buffer_Size];
	
	int i = 0;
	int j = 0;
	
	// Read file
	while(fgets(buffer, Buffer_Size, input_file)){
		process_matrix(buffer);
		
		for(j = 0; buffer[j]; j++)
			matrix[i][j] = (int) (buffer[j] == '1');
		
		i++;
	}
	fclose(input_file);
	
	int size = i;
	
	char temp[100000];
	
	sprintf(temp, "graph([\0");
	
	
	for (i = 0; i < size; i++)
		sprintf(temp+strlen(temp), "v%d, \0", i+1);
	sprintf(temp+strlen(temp)-2, "], [\0", i+1);
	
	for (i = 0; i < size; i++)
		for (j = i+1; j < size; j++)
			if(matrix[i][j])
				sprintf(temp+strlen(temp), "[v%d, v%d], \0", i+1, j+1);
	
	sprintf(temp+strlen(temp)-2, "]).\n\0", i+1, j+1);
	
	// Create output file
	char filename[100];
	sprintf(filename, "bipartite%d.pl", size);
	FILE *output = fopen(filename, "w");
    if (output == NULL)
    {
        printf("Error: could not open file");
        return 1;
    }
	
	fprintf(output, "colors([red, blue]).\n\n");
	fprintf(output, "%s\n", temp);
	
	fprintf(output, "len([],0).\n");
	fprintf(output, "len([_|R], N) :- len(R,M), N is M+1.\n\n");
	
	fprintf(output, "color(C) :- colors(L), member(C,L).\n\n");
	
	fprintf(output, "vertex(V) :- graph(L,_), member(V,L).\n\n");
	
	fprintf(output, "edge(X,Y) :- graph(_,L), member([X,Y], L).\n");
	fprintf(output, "edge(X,Y) :- graph(_,L), member([Y,X], L).\n\n");
	
	fprintf(output, "conflict([V,_], [[V,_]|_]).\n");
	fprintf(output, "conflict([V,C], [[U,C]|_]) :- edge(V,U).\n");
	fprintf(output, "conflict([V,C], [_|R]) :- conflict([V,C], R).\n\n");
	
	fprintf(output, "find_color(_, _, [], Count, NumVertices) :-\n\t");
	fprintf(output, "Count =:= NumVertices.\n\n");
    
	fprintf(output, "find_color([X,C], Coloured, [[X,C]|R], Count, NumVertices) :-\n\t");
	fprintf(output, "color(C),\n\t");
	fprintf(output, "vertex(X),\n\t");
    fprintf(output, "\\+(conflict([X,C], Coloured)),\n\t");
    fprintf(output, "M is Count + 1,\n\t");
    fprintf(output, "find_color(_, [[X,C]|Coloured], R, M, NumVertices).\n\n");
    
	fprintf(output, "checkbipartite :-\n\t");
	fprintf(output, "graph(V,_),\n\t");
    fprintf(output, "len(V,NumVertices),\n\t");
    fprintf(output, "find_color(_,[],_, 0, NumVertices).\n\n");

	fclose(output);
	return 0;
	
	
}
