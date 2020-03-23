
/* WARNING: THIS PROGRAM IS THE PROPERTY AND COPYRIGHT OF DR.ZIAD KOBTI
 NO PART OF THIS CODE MAY BE COPIED OR USED IN ASSIGNMENT SUBMISSION
 THIS CODE IS INTENDED TO SUPPLEMENT THE CLASS TUTORIAL AND FOR EDUCATION
 PURPOSES ONLY FOR THE CURRENTLY REGISTERED 60-141 STUDENTS AT THE UNIVERSITY OF WINDOR */
// (C) 2016 DR ZIAD KOBTI. ALL RIGHTS RESERVED

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 5
#define DEBUG 1

/* write a program that can maintain a list of the top MAX scores */

// structure defintion of the high score for our game
struct highscore
{
    int score;      // maintain the score
    char name[41];  // name of the player up to 40 usable characters
};
// create an alias for highscore
typedef struct highscore Highscore;

/* Prototypes */
int InputPositiveNumber(int max);
Highscore* InputHighscore(Highscore *ptr_Highscore);
int AddHighscoreAtIndex(Highscore list[], Highscore hs, int i);
int InsertionSort(Highscore list[], int *ptr_size, Highscore hs);
void DisplayHighscore(Highscore hs);
void DisplayHighscoreList(Highscore list[], int size);
int DeleteHighscore(Highscore list[], int i, int *ptr_size);
int SaveHighscoreList(char *filename, Highscore list[], int size);
int LoadHighscoreList(char *filename, Highscore list[], int *ptr_size);
char* encode(char*s);
char* decode(char*s);

/* Read input from the user stdin looking for a positive integer between 0 and max
 I: the range of the integer 0 and max inclusive
 O: return the user input between 0 and max inclusive
 */

int InputPositiveNumber(int max)
{
    int errorFlag=0;
    int temp;
    char user_input[99];
    
    do // will nag the user endlessly until a valid input is received
    {
        errorFlag=0;
        printf("Please enter a positive integer up to %d: ", max);
        fgets(user_input,99,stdin);
        sscanf(user_input, "%d", &temp);
        if (temp < 0 || temp > max)
        {
            printf("Invalid input\n");
            errorFlag=1;
        }
    }while(errorFlag==1);
    
    return temp;
}

/* Prompt the user to enter the values for one event;
 I: a pointer to the event you want to populate its values from the user
 O: return a pointer to the event
 */
Highscore* InputHighscore(Highscore *ptr_Highscore)
{
    if (ptr_Highscore != NULL) // quality assurance: make sure the pointer is valid
    {
        printf("Enter the score:\n");
        ptr_Highscore->score = InputPositiveNumber(100);
        printf("Enter the name: ");
        gets(ptr_Highscore->name);
    }
    return ptr_Highscore;
}

/* add an event to the array list[] at index i
 I: the array, event and index must be valid
 O: updates the list with the new event added at index i, overwriting it. return i
 */
int AddHighscoreAtIndex(Highscore list[], Highscore hs, int i)
{
    if (i < 0 || i > MAX) return -1;
    
    list[i].score = hs.score;
    strcpy(list[i].name, hs.name);
    
    return i;
}

/* uses insertion sort to add an event to the list
 I: the list, its size by reference and the event to add
 O: will add the event to the list in order of increasing hh:mm and return new size
 */
int InsertionSort(Highscore list[], int *ptr_size, Highscore hs)
{
    int p=0;
    int i;
    int done = 0;
    
    if (*ptr_size >= MAX) return -1; // quality assurance: limit exceeded
    
    // find the insertion point p
    while (!done && *ptr_size>0)
    {
        if (hs.score <= list[p].score)
            done = 1;
        else
            p++;
        
        if (p==*ptr_size)
            done = 1;
    }
    // shift p on, to p+1 one
    for (i=*ptr_size; i>p; i--)
    {
        AddHighscoreAtIndex(list, list[i-1], i);
    }
    
    AddHighscoreAtIndex(list, hs, p);
    (*ptr_size)++;
    
    return p;
}

/* will display the score */
void DisplayHighscore(Highscore hs)
{
    printf("%05d: %s\n",
           hs.score, hs.name);
}


void DisplayHighscoreList(Highscore list[], int size)
{
    int i;
    for(i=0; i<size; i++)
    {
        printf("[%d] ", i);
        DisplayHighscore(list[i]);
    }
}

/* removes an event at a given index from the list and updates its size */
int DeleteHighscore(Highscore list[], int i, int *ptr_size)
{
    int d;
    for(d=i+1; d<*ptr_size; d++)
    {
        list[d-1].score=list[d].score;
        strcpy(list[d-1].name, list[d].name);
    }
    (*ptr_size)--;
    return *ptr_size;
}

/* save the list of events to a given file */
int SaveHighscoreList(char *filename, Highscore list[], int size)
{
    int i;
    FILE *fn;
    fn = fopen(filename, "w");
    if (fn!=NULL)
    {
        for(i=0;i<size;i++)
        {
            if(i!=0) fprintf(fn, "\n"); // eliminates last line in file
            fprintf(fn, "%d %s",
                    list[i].score,
                    encode(list[i].name));
            decode(list[i].name); // put spaces back after saving!
        }
        fclose(fn);
        return size;
    }
    return -1;
}

/* retrieves the list from a file and updates its size, existing list will be overwritten */
int LoadHighscoreList(char *filename, Highscore list[], int *ptr_size)
{
    int i=0;
    FILE *fn;
    fn = fopen(filename, "r");
    if (fn!=NULL)
    {
        while(!feof(fn))
        {
            fscanf(fn, "%d%s",
                   &list[i].score,
                   list[i].name);
            decode(list[i].name);
            i++;
        }
        *ptr_size=i;
        fclose(fn);
        return i;
    }
    return -1;
    
}

/* convert space characters to underscore */
char* encode(char*s)
{
    char *p=s;
    while(*p!='\0')
    {
        if(*p ==' ') *p='_';
        p++;
    }
    return s;
}

/* convert underscore characters to spaces */
char* decode(char*s)
{
    char *p=s;
    while(*p!='\0')
    {
        if(*p=='_') *p=' ';
        p++;
    }
    return s;
}

/* MAIN program starts here */
int main()
{
    Highscore HighscoreList[MAX];       // maintains the list of high scores
    int HighscoreListSize = 0;      // maintains the size of HighscoreList
    int done = 0;               // flag for main menu
    int n;                      // a user defined and temporary variable
    char filename[65];          // 64 characters max
    Highscore tempHighscore;
    
    DisplayHighscoreList(HighscoreList, MAX);
    
    do  // main menu
    {
        printf("__= MyGame v1.0 =__\n");
        printf("   1. Add score\n");
        printf("   2. Delete score.\n");
        printf("   3. Display scores.\n");
        printf("   4. Save scores.\n");
        printf("   5. Load scores.\n");
        printf("   0. Exit\n");
        printf("==> ");
        
        n = InputPositiveNumber(5);   // You shall not pass unless it is in the range 1 and 6
        
        switch(n)
        {
            case 1: // Schedule an event: input new event and insert it to list
                if (HighscoreListSize < MAX)
                {
                    InsertionSort(HighscoreList,
                                  &HighscoreListSize,
                                  *InputHighscore(&tempHighscore));
                }
                else
                {
                    
                    printf("The maximum number of scores you can add has been reached!\n");
                    printf("We apologize for any inconvenience this may cause.\n");
                }
                break;
            case 2:
                if (HighscoreListSize >0)
                {
                    DisplayHighscoreList(HighscoreList, HighscoreListSize);
                    printf("Which score you like to delete?\n");
                    n= InputPositiveNumber(HighscoreListSize-1);
                    DeleteHighscore(HighscoreList, n, &HighscoreListSize);
                }
                else
                {
                    printf("You need to have a score to delete first...\n");
                }
                break;
            case 3: // Display the scores
                if (HighscoreListSize >0)
                {
                    DisplayHighscoreList(HighscoreList, HighscoreListSize);
                }
                else
                {
                    printf("You need to have a score to display first...\n");
                }
                break;
            case 4:
                printf("File name: ");
                gets(filename);
                n=SaveHighscoreList(filename, HighscoreList, HighscoreListSize);
                if (n>-1)
                {
                    printf("%d scores saved into file named %s.\n", n, filename);
                }
                else
                {
                    printf("Something is wrong, nothing got saved!\n");
                }
                break;
            case 5:
                printf("This action will replace the current scores.\nFile name (or \"abort\" to cancel operation): ");
                gets(filename);
                if (strcasecmp(filename, "abort")==0)
                    break;
                n=LoadHighscoreList(filename, HighscoreList, &HighscoreListSize);
                if (n>-1)
                {
                    printf("%d scores loaded into file named %s.\n", n, filename);
                }
                else
                {
                    printf("Something is wrong, nothing got saved!\n");
                }
                break;
            case 0: // exit
                done = 1;
                // there is no default because it is not possible due to the locked down inputRange
        }
        
    }while(!done);
    
    printf("Goodbye...\n\n"); // farewell, adieu ... hope you saved first!
    
    return 0;
}

