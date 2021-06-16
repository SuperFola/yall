package io.github.superfola.lexer

import cats.implicits.toTraverseOps

import scala.annotation.tailrec

final case class PartialToken(token: Token, rest: List[Char])

object Lexer {
  private def charType(c: Char): CharType =
    c match {
      case '('                               => LParenType()
      case ')'                               => RParenType()
      case x if x.isDigit                    => NumberType(x)
      case x if x.isLetter                   => SymbolType(x)
      case '+' | '-' | '*' | '/' | ',' | '?' => SymbolType(c)
      case x if x.isWhitespace               => SpaceType()
      case x                                 => MismatchType(x)
    }

  @tailrec
  def readNumber(token: NumberToken, list: List[Char]): PartialToken =
    list match {
      case x :: xs =>
        charType(x) match {
          case NumberType(value) => readNumber(NumberToken(token.value + value), xs)
          case _                 => PartialToken(token, x :: xs)
        }
      case Nil => PartialToken(token, Nil)
    }

  @tailrec
  def readSymbol(token: SymbolToken, list: List[Char]): PartialToken =
    list match {
      case x :: xs =>
        charType(x) match {
          case SymbolType(value) => readSymbol(SymbolToken(token.value + value), xs)
          case _                 => PartialToken(token, x :: xs)
        }
      case Nil => PartialToken(token, Nil)
    }

  private def readTokens(list: List[Char]): List[Option[Token]] = {
    list match {
      case x :: xs =>
        charType(x) match {
          case LParenType() => Some(LParenToken()) :: readTokens(xs)
          case RParenType() => Some(RParenToken()) :: readTokens(xs)
          case NumberType(value) =>
            val PartialToken(token, rest) = readNumber(NumberToken(value.toString), xs)
            Some(token) :: readTokens(rest)
          case SymbolType(value) =>
            val PartialToken(token, rest) = readSymbol(SymbolToken(value.toString), xs)
            Some(token) :: readTokens(rest)
          case SpaceType()         => readTokens(xs)
          case MismatchType(value) => List(None)
        }
      case Nil => Some(EOFToken()) :: Nil
    }
  }

  def fromString(source: String): Option[List[Token]] = {
    readTokens(source.toList).sequence
  }
}
