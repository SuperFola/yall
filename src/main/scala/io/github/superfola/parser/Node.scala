package io.github.superfola.parser

import io.github.superfola.lexer.SymbolToken

sealed trait Node

final case class EmptyNode()                                                 extends Node
final case class NumberNode(value: Double)                                   extends Node
final case class SymbolNode(value: SymbolToken)                              extends Node
final case class TupleNode(left: Node, right: Node)                          extends Node
final case class ExpressionNode(expr: SymbolToken, children: List[Node])     extends Node
final case class DefineNode(symbol: SymbolToken, value: Node)                extends Node
final case class LocalDefineNode(defines: List[DefineNode], expr: Node)      extends Node
final case class FunctionNode(symbol: SymbolToken, body: Node)               extends Node
final case class AffectationNode(symbol: SymbolToken, value: Node)           extends Node
final case class ConditionNode(condition: Node, ifTrue: Node, ifFalse: Node) extends Node
final case class LoopNode(condition: Node, body: Node)                       extends Node
