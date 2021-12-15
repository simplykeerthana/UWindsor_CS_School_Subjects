#lang racket

(define (weblink y link)
  (if (null? link)'()
       (if (member? y (cdr (car link)))
           (append
              (list (car (car link)))
              (weblink y (cdr link)))
           (weblink y (cdr link)))))
           
(define (member? x lst)
  (cond ((null? lst) #f)
        ((equal? x (car lst)) #t)
        (else (member? x (cdr lst)))))              
         
(define (graph link sublist)
  (if (null? sublist)'()
      (append
         (list (append
            (list (car (car sublist)))
            (weblink (car (car sublist)) link)))
         (graph link (cdr sublist)))))

(define (reverseWL list) (graph list list))

