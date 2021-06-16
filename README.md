# yall - Yet Anoter Lisp Like

## Syntax

- Prefixed expressions: `(+ 2 (* 3 4))`
- Variable definition: `(define six 6)`
- Affectation: `(set! six 7)`
- Conditions: `(if (not (> 3 (+ 4 2))) (* 3 6) 5)`
- Loops:
```
(while (< i 10)
  (begin
    (set! sum (+ 1 sum))
    (set! i (+ 1 i))))
```
- Local variable definitions:
```
(+
  (let ((x 3) (y 2))
  (* x y))
  (/ 9 4))
```
- Functions:
```
(define (fact n)
    (if (= n 0) 1
        (* n (fact (- n 1)))))
(fact 8)
```
- Tuples:
```
(define list (, 1 (, 2 3)))
(nth 0 (nth 1 list))
```