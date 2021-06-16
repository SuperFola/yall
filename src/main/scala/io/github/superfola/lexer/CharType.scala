package io.github.superfola.lexer

sealed trait CharType

final case class LParenType()              extends CharType
final case class RParenType()              extends CharType
final case class NumberType(value: Char)   extends CharType
final case class SymbolType(value: Char)   extends CharType
final case class SpaceType()               extends CharType
final case class MismatchType(value: Char) extends CharType
