package io.github.superfola.parser

import io.github.superfola.lexer._

object Parser {
  def fromTokens(tokens: List[Token]): Either[String, Node] =
    tokens match {
      case x :: xs =>
        x match {
          case SymbolToken(value) => Right(SymbolNode(SymbolToken(value)))
          case NumberToken(value) =>
            value.toDoubleOption.map(v => NumberNode(v)) match {
              case Some(value) => Right(value)
              case None        => Left(s"Couldn't parse number '$value'")
            }
          case LParenToken() =>
            xs.head match {
              case SymbolToken(value) => ???
              case NumberToken(_)     => Left("Unexpected Number after LParen")
              case LParenToken()      => Left("Unexpected LParen after LParen")
              case RParenToken()      => Right(EmptyNode())
              case EOFToken()         => Left("Unexpected end of file")
            }
          case RParenToken() => ???
          case EOFToken()    => ???
        }
      case Nil => ???
    }
}
