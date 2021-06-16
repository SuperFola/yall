package io.github.superfola.parser

import io.github.superfola.lexer._

final case class PartialNode(node: Node, rest: List[Token])

object Parser {
  private def createTree(list: List[Token]): Option[PartialNode] =
    list match {
      case LParenToken() :: SymbolToken(name) :: xs =>
        name match {
          case "define" =>
            createTree(xs).flatMap { case PartialNode(SymbolNode(s), rest) =>
              createTree(rest).map { case PartialNode(expr, rest) =>
                PartialNode(DefineNode(SymbolNode(s), expr), rest)
              }
            }
          case "set!" =>
            createTree(xs).flatMap { case PartialNode(SymbolNode(s), rest) =>
              createTree(rest).map { case PartialNode(expr, rest) =>
                PartialNode(AffectationNode(SymbolNode(s), expr), rest)
              }
            }
          case "if"    => Some(PartialNode(ConditionNode(???, ???, ???), xs))
          case "while" => Some(PartialNode(LoopNode(???, ???), xs))
          case "begin" => Some(PartialNode(BeginNode(???), xs))
          case _       => Some(PartialNode(ExpressionNode(SymbolNode(SymbolToken(name)), ???), xs))
        }
      case x :: xs =>
        x match {
          case SymbolToken(value) => Some(PartialNode(SymbolNode(SymbolToken(value)), xs))
          case NumberToken(value) =>
            value.toDoubleOption
              .map(d => PartialNode(NumberNode(d), xs))
          case RParenToken() | EOFToken() => None
        }
      case Nil => None
    }

  def fromTokens(tokens: List[Token]): Option[Node] =
    createTree(tokens).flatMap { case PartialNode(node, rest) =>
      if (rest.isEmpty) {
        Some(node)
      } else {
        fromTokens(rest)
          .map(otherNode => BeginNode(List(node, otherNode)))
      }
    }
}
