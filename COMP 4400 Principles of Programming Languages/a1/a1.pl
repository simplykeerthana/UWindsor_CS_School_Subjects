%Name: Keerthana Madhavan

%Course: COMP 4400 | Assignment 1
%Write a prolog program to find a knight path on a given N*N chessboard, where N is an input.
% A knight path must visit each square exactly once. It starts from the fixed location (1,1).
% It may not be a knight tour. You must define all predicates including operations on the lists.
%the nightpatth takes in two values the size of the board, and it always starts at 1/1.
%
% jump distances
%move down 1 cells right 2 cells
 move_direction(1,2).
%move down 2 cells right 1 cells
 move_direction(2,1).
%move down 2 cells left 1 cells
 move_direction(2,-1).
%move down 1 cells left 2 cells
 move_direction(1,-2).
%move up 1 cells left 2 cells
 move_direction(-1,-2).
%move up 2 cells left 1 cells
 move_direction(-2,-1).
%move up 2 cells right 1 cells
 move_direction(-2,1).
%move down 1 cells right 2 cells
 move_direction(-1,2).

%This will build a Path between N and M by calling knight_path/4
knight_path(N,Knights) :- M is N*N-1, knight_path(N,M,[1/1],Knights).
knight_path(_,0,Knights,Knights).
%check for diagnols, visited, if a member or not and etc.
% knights(N,M,Visited,Knights) :- the list of squares Visited list
%Recursive case to find a move from x to Y.
knight_path(N,M,Visited,Knights) :- Visited = [X/Y|_],
move(N,X/Y,U/V),\+ memberchk(U/V,Visited),
K is M-1, knight_path(N,K,[U/V|Visited],Knights).
move(N,A/B,C/D) :- move_direction(X,Y), C is A+X, C > 0, C =< N, D is B+Y, D > 0, D =< N.






