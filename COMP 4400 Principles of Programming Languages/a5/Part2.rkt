#lang racket

(define (subst e x vbind)
  (if (checkexpr vbind)
      (subexp e x vbind #f)vbind))

(define (checkexpr vbind)
  (if (list? vbind)
      (if (equal? (length vbind) 2)
           (and (checkexpr (car vbind)) (checkexpr (car (cdr vbind))))
           (if (equal? (length vbind) 3)
               (if (equal? (car vbind) 'lmda)
                   (and (checkexpr (car (cdr vbind))) (checkexpr (car (cdr (cdr vbind)))))#f)#f))
      (if (equal? vbind 'lmda)#f #t)))

(define (subexp e x vbind bound?)
  (if (list? vbind)
      (if (equal? (car vbind) 'lmda)
          (if (equal? (car (cdr vbind)) x)
              (list 'lmda x (subexp e x (car (cdr (cdr vbind))) #t))
              (list 'lmda (car (cdr vbind)) (subexp e x (car (cdr (cdr vbind))) #f)))
          (list (subexp e x (car vbind) bound?) (subexp e x (car (cdr vbind)) bound?)))
      (if bound? vbind (if (equal? vbind x) e vbind))))

