Step1.pl 

The crossword will create a map of each words and finds the correct fit based on the character list. 
The predicate word, has the character list of each words the prof provided. From Ln 41, the words are placed based on the bin map. 
This has 2 solutions. 

Step2.pl

Took a different approach here because, it should be generalized The C program generator.c generated the C file. So basically, it reads each line from 
word1.txt and converts it into a character list and creates a array of list. Same goes for the binpattern1.txt. 

The crossword predicate takes in two files file1 (words), file2(pattern). 
The separate predicate, processs the character list. 
The squares or box creation, will choose the right words based on check_length and creates the boxes based on the binpatterns. The find_squares predicate will 
then find the appropriate squares based on length and squares availability and fill it in. The prolog code will also generate selected words.txt with the 
character list of selected words. The code will also output the crossword result. 

I didn’t have time to figure out the display.c.

How to run the program. 

Step 1. 

Gprolog
[step1].
crossword(W1, W2, W3, W4, W5, W6, W7, W8, W9, W10, W11).
It will give the answer.


Step2

gcc generator.c 
./a.out

This will create step2.pl in the directory. 

Then run step2.pl 

gprolog 
[step2].
crossword(‘word1.txt’, ‘binpattern1.txt’).

This will create selected words.txt and as well as output the puzzle to the screen. 

Please see screenshots of working code in submission zip file. 


