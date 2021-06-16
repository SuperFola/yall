package io.github.superfola

import io.github.superfola.TestsHelper._
import io.github.superfola.lexer._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LexerSpec extends AnyFlatSpec with Matchers {
  "A lexer" should "generate a list of tokens" in {
    Lexer.fromString(addition) should be(Some(List(
      LParenToken(),
      SymbolToken("+"),
      NumberToken("2"),
      LParenToken(),
      SymbolToken("*"),
      NumberToken("3"),
      NumberToken("4"),
      RParenToken(),
      RParenToken(),
      EOFToken()
    )))
  }
}
