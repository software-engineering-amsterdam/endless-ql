package expression.visitor

import expression.BinaryExpression
import expression.Expression
import expression.LiteralExpression
import expression.UnaryExpression


interface ExpressionVisitor<out O> {

    fun visit(expression: Expression): O

    fun visit(literal: LiteralExpression): O

    fun visit(unary: UnaryExpression): O

    fun visit(binary: BinaryExpression): O

}