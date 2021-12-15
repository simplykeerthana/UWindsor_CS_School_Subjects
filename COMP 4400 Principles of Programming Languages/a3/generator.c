#include <stdio.h>
#include <stdlib.h>

#define DATA_SIZE 5000

int main()
{
    // Char arrays are declared like so:
//char array[] = "read_lines(File,Lines) :- seeing(Old), see(File), get_char(X), read_file(X,CharList), parse_charlist(CharList-[],Lines), see(Old).";
char array[] = "read_lines(File,Lines) :- seeing(Old), see(File), get_char(X), read_file(X,CharList), parse_charlist(CharList-[],Lines), see(Old)." "\n"

"read_file(end_of_file,[]) :- !.""\n"
"read_file(X,[X|Xs]) :- get_char(Y), read_file(Y,Xs).""\n"

"parse_charlist(T-T,[]) :- !.""\n"
"parse_charlist(X1-X4,[L|Ls]) :- parse_line(X1-X2,L), parse_eol(X2-X3), !, parse_charlist(X3-X4,Ls).""\n"

"parse_eol([]-[]) :- !.""\n"           
"parse_eol(['\\n'|R]-R).""\n"    

"parse_line([]-[],[]) :- !.""\n"       
"parse_line([X|X1]-[X|X1],[]) :- eol_char(X), !.""\n"
"parse_line([X|X1]-X2,[X|Xs]) :- \\+ eol_char(X), parse_line(X1-X2,Xs).""\n"
"eol_char('\\r').""\n"
"eol_char('\\n').""\n"

"crossword(File1,File2) :- read_lines(File1,Lines1), read_lines(File2, Lines2), append(Lines1, Lines2, Lines), separate(Lines,Words,FrameLines), length(Words,NWords), open('selectedwords.txt',write,Stream), write(Stream, Words),  nl(Stream), close(Stream), f_squares(FrameLines,Squares,MaxRow,MaxCol), construct_sites(Squares,MaxRow,MaxCol,Sites), length(Sites,NSites), check_lengths(NWords,NSites), find_words(Words,Sites), draw(Squares,MaxRow,MaxCol).""\n"

"check_lengths(N,N) :- !.""\n"
"check_lengths(NW,NS) :- NW \\= NS, fail.""\n"

"separate(Lines,Words,FrameLines) :- trim_lines(Lines,LinesT), parse_non_empty_lines(LinesT-L1,Words),parse_empty_lines(L1-L2), parse_non_empty_lines(L2-L3,FrameLines), parse_empty_lines(L3-[]).""\n"

"trim_lines([],[]).""\n"
"trim_lines([L|Ls],[LT|LTs]) :- trim_line(L,LT), trim_lines(Ls,LTs).""\n"

"trim_line(L,LT) :- reverse(L,RL), rm_white_space(RL,RLT), reverse(RLT,LT).""\n"

":-dynamic(char_type/2).""\n"
"rm_white_space([X|Xs],L) :- char_type(X,whitespace),!, rm_white_space(Xs,L).""\n"
"rm_white_space(L,L).""\n"      

"parse_non_empty_lines([L|L1]-L2,[L|Ls]) :- L \\= [], !, parse_non_empty_lines(L1-L2,Ls).""\n"
"parse_non_empty_lines(L-L,[]).""\n"

"parse_empty_lines([[]|L1]-L2) :- !, parse_empty_lines(L1-L2).""\n"
"parse_empty_lines(L-L).""\n"

"f_squares(FrameLines,Squares,MaxRow,MaxCol) :-  f_squares(FrameLines,SquaresList,1), flatten(SquaresList,Squares),maxima(Squares,0,0,MaxRow,MaxCol).""\n"

"f_squares([],[],_).""\n"                     
"f_squares([FL|FLs],[SL|SLs],Row) :- construct_squares_row(FL,SL,Row,1), Row1 is Row+1, f_squares(FLs,SLs,Row1).""\n"

"construct_squares_row([],[],_,_).""\n"                       
"construct_squares_row(['.'|Ps],[sq(Row,Col,_)|Sqs],Row,Col) :- !, Col1 is Col+1, construct_squares_row(Ps,Sqs,Row,Col1).""\n"
"construct_squares_row([X|Ps],[sq(Row,Col,X)|Sqs],Row,Col) :- char_type(X,alpha), !, Col1 is Col+1, construct_squares_row(Ps,Sqs,Row,Col1).""\n"
"construct_squares_row([_|Ps],Sqs,Row,Col) :-  Col1 is Col+1, construct_squares_row(Ps,Sqs,Row,Col1).""\n"


"maxima([],MaxRow,MaxCol,MaxRow,MaxCol).""\n"
"maxima([sq(Row,Col,_)|Sqs],AccRow,AccCol,MaxRow,MaxCol) :-AccRow1 is max(AccRow,Row),AccCol1 is max(AccCol,Col), maxima(Sqs,AccRow1,AccCol1,MaxRow,MaxCol).""\n"

"construct_sites(Squares,MaxRow,MaxCol,Sites) :- construct_sites_h(Squares,MaxRow,MaxCol,1,SitesH,[]),  construct_sites_v(Squares,MaxRow,MaxCol,1,Sites,SitesH).""\n" 

"construct_sites_h(_,MaxRow,_,Row,Sites,Sites) :- Row > MaxRow, !.""\n"
"construct_sites_h(Squares,MaxRow,MaxCol,Row,Sites,AccSites) :- construct_sites_h(Squares,MaxRow,MaxCol,Row,1,AccSites1,AccSites), Row1 is Row+1, construct_sites_h(Squares,MaxRow,MaxCol,Row1,Sites,AccSites1).""\n"

"construct_sites_h(_,_,MaxCol,_,Col,Sites,Sites) :- Col > MaxCol, !.""\n"
"construct_sites_h(Squares,MaxRow,MaxCol,Row,Col,Sites,AccSites) :- construct_site_h(Squares,Row,Col,Site,Incr), !, Col1 is Col+Incr, AccSites1 = [Site|AccSites], construct_sites_h(Squares,MaxRow,MaxCol,Row,Col1,Sites,AccSites1).""\n"
   
"construct_sites_h(Squares,MaxRow,MaxCol,Row,Col,Sites,AccSites) :- Col1 is Col+1, construct_sites_h(Squares,MaxRow,MaxCol,Row,Col1,Sites,AccSites).""\n"

"construct_site_h(Squares,Row,Col,[X,Y|Cs],Incr) :- memberchk(sq(Row,Col,X),Squares), Col1 is Col+1, memberchk(sq(Row,Col1,Y),Squares), Col2 is Col1+1, continue_site_h(Squares,Row,Col2,Cs,3,Incr).""\n"

"continue_site_h(Squares,Row,Col,[X|Cs],Acc,Incr) :- memberchk(sq(Row,Col,X),Squares), !, Acc1 is Acc+1, Col1 is Col+1, continue_site_h(Squares,Row,Col1,Cs,Acc1,Incr).""\n"

"continue_site_h(_,_,_,[],Incr,Incr).""\n"


"construct_sites_v(_,_,MaxCol,Col,Sites,Sites) :- Col > MaxCol, !.""\n"
"construct_sites_v(Squares,MaxRow,MaxCol,Col,Sites,AccSites) :- construct_sites_v(Squares,MaxRow,MaxCol,1,Col,AccSites1,AccSites), Col1 is Col+1, construct_sites_v(Squares,MaxRow,MaxCol,Col1,Sites,AccSites1).""\n"

"construct_sites_v(_,MaxRow,_,Row,_,Sites,Sites) :- Row > MaxRow, !.""\n"
"construct_sites_v(Squares,MaxRow,MaxCol,Row,Col,Sites,AccSites) :- construct_site_v(Squares,Row,Col,Site,Incr), !, Row1 is Row+Incr, AccSites1 = [Site|AccSites], construct_sites_v(Squares,MaxRow,MaxCol,Row1,Col,Sites,AccSites1).""\n"

"construct_sites_v(Squares,MaxRow,MaxCol,Row,Col,Sites,AccSites) :- Row1 is Row+1, construct_sites_v(Squares,MaxRow,MaxCol,Row1,Col,Sites,AccSites).""\n"

"construct_site_v(Squares,Row,Col,[X,Y|Cs],Incr) :- memberchk(sq(Row,Col,X),Squares), Row1 is Row+1, memberchk(sq(Row1,Col,Y),Squares), Row2 is Row1+1, continue_site_v(Squares,Row2,Col,Cs,3,Incr).""\n"

"continue_site_v(Squares,Row,Col,[X|Cs],Acc,Incr) :- memberchk(sq(Row,Col,X),Squares), !, Acc1 is Acc+1, Row1 is Row+1, continue_site_v(Squares,Row1,Col,Cs,Acc1,Incr).""\n"

"continue_site_v(_,_,_,[],Incr,Incr).""\n"
    

"find_words([],[]).""\n"
"find_words([W|Words],Sites) :- select(W,Sites,SitesR), find_words(Words,SitesR).""\n"

"draw(Squares,MaxRow,MaxCol) :- draw(Squares,MaxRow,MaxCol,1), nl.""\n"

"draw(_,MaxRow,_,Row) :- Row > MaxRow, !.""\n"
"draw(Squares,MaxRow,MaxCol,Row) :- draw(Squares,MaxRow,MaxCol,Row,1), nl, Row1 is Row+1, draw(Squares,MaxRow,MaxCol,Row1).""\n"

"draw(_,_,MaxCol,_,Col) :- Col > MaxCol, !.""\n" 

"draw(Squares,MaxRow,MaxCol,Row,Col) :- (memberchk(sq(Row,Col,X),Squares), !, write(X); write(' ')), Col1 is Col+1, draw(Squares,MaxRow,MaxCol,Row,Col1).""\n"  ;


// Open a file for writing. 
// (This will replace any existing file. Use "w+" for appending)

FILE *file = fopen("step2.pl", "w");

int results = fputs(array, file);
if (results == EOF) {
    // Failed to write do error code here.
}
fclose(file);

    return 0;
}