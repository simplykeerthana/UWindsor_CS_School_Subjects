%Name: Keerthana Madhavan

%Course: COMP 4400 | Assignment 2
% Write a prolog program that tries to assign the given colors to the regions of the given map.
%recursion to find neighbors
find_neighbour([],R,R).

%a simple conditional statement in prolog to generate negihbors based on the adjacency list.
%This recursion relation will check if adjacent regions can be connected to another or not.
find_neighbour([[X,Y]|S], R,A) :- (member(X,R) -> (member(Y,R) -> find_neighbour(S,R,A) ; find_neighbour(S,[Y|R],A)) ;(member(Y,R) -> 
find_neighbour(S,[X|R],A) ; find_neighbour(S,[X,Y|R],A))).
  
%CHANGEABLE A map predicate here you would want to change the list of lists to test
map([[1,2], [1,3], [2,3],[3,4], [5,6], [5,7]]).

%CHANGEABLE A color predicate here you would want to change the list of colors. minimum is two colors.

color([red,green,blue,pink,violet]).

result(Coloring) :- map(L), color(H),find_neighbour(L,[],Regions), colorit(Regions,H,Coloring), \+ valid(L,Coloring).

colorit([R|Rs],X,[[R,C]|A]) :- member(C,X),colorit(Rs,X,A). %recursion
colorit([],_,[]).
%the adjacency list takes tthe list of neighbor regions to check if 1 and 2 are adjacent on [1,2] and to see if [2,1] are the same. 

adjacent(X,Y,Map) :-  member([X,Y],Map) ; member([Y,X],Map).
%with the valid predicate it will check if two adjacent regions or not then assign  
valid(Map,Coloring) :- member([R1,C],Coloring), member([R2,C],Coloring), adjacent(R1,R2,Map).


