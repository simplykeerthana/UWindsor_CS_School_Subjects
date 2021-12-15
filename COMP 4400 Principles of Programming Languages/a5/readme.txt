
Part 1
The objective is to given a web link graph the program should reverse a web link graph so that for  each link
ouput the reversed version. 

How to run part 1 in Dr. Racket

(reverseWL '((a b c) (b c e) (c b a) (e)))

Part 2
Lambda Substitution with Dynamic Binding

run the test cases as in Drracket

(subst 'e 'x '(lmda x x))
(subst 'e 'x '(lmda y (lmda x x)))
(subst 'e 'x '(lmda y (lmda y x)))
(subst 'e 'x 'x)
(subst 'e 'x '((x x) y))
(subst 'e 'x '(x x y))


