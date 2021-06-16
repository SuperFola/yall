package io.github.superfola.lexer

sealed trait Token

final case class SymbolToken(value: String) extends Token
final case class NumberToken(value: String) extends Token
final case class LParenToken()              extends Token
final case class RParenToken()              extends Token
