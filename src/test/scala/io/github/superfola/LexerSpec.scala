package io.github.superfola

import io.github.superfola.TestsHelper._
import io.github.superfola.lexer._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LexerSpec extends AnyFlatSpec with Matchers {
  "A lexer" should "work with an addition" in {
    Lexer.fromString(addition) should be(Some(List(
      LParenToken(),
      SymbolToken("+"),
      NumberToken("2"),
      LParenToken(),
      SymbolToken("*"),
      NumberToken("3"),
      NumberToken("42"),
      RParenToken(),
      RParenToken(),
      EOFToken()
    )))
  }
  it should "work with variable definition" in {
    Lexer.fromString(definition) should be(Some(List(
      LParenToken(),
      SymbolToken("define"),
      SymbolToken("six"),
      NumberToken("6"),
      RParenToken(),
      EOFToken()
    )))
  }
  it should "work with variable affectation" in {
    Lexer.fromString(affectation) should be(Some(List(
      LParenToken(),
      SymbolToken("set!"),
      SymbolToken("six"),
      NumberToken("7"),
      RParenToken(),
      EOFToken()
    )))
  }
  it should "work with condition" in {
    Lexer.fromString(condition) should be(Some(List(
      LParenToken(),
      SymbolToken("if"),
      LParenToken(),
      SymbolToken("not"),
      LParenToken(),
      SymbolToken(">"),
      NumberToken("3"),
      LParenToken(),
      SymbolToken("+"),
      NumberToken("4"),
      NumberToken("2"),
      RParenToken(),
      RParenToken(),
      RParenToken(),
      LParenToken(),
      SymbolToken("*"),
      NumberToken("3"),
      NumberToken("6"),
      RParenToken(),
      NumberToken("5"),
      RParenToken(),
      EOFToken()
    )))
  }
}
