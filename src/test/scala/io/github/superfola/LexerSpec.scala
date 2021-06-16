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
}
