package doge.ast.node

import doge.ast.node.expression.Expression

class IfStatement(val expression: Expression, val block: Block) : Statement