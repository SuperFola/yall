package io.github.superfola

object TestsHelper {
  val addition    = "(+ 2 (* 3 42))"
  val definition  = "(define six 6)"
  val affectation = "(set! six 7)"
  val condition   = "(if (not (> 3 (+ 4 2))) (* 3 6) 5)"
  val loop: String =
    """
      |(while (< i 10)
      |  (begin
      |    (set! sum (+ 1 sum))
      |    (set! i (+ 1 i))))
      |""".stripMargin
  val localVariable: String =
    """
      |(+
      |  (let ((x 3) (y 2))
      |  (* x y))
      |  (/ 9 4))
      |""".stripMargin
  val functionDef: String =
    """
      |(define (fact n)
      |    (if (= n 0) 1
      |        (* n (fact (- n 1)))))
      |(fact 8)
      |""".stripMargin
  val tupleDef: String =
    """
      |(define list (, 1 (, 2 3)))
      |(nth 0 (nth 1 list))
      |""".stripMargin
}
