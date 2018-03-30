package doge.ast.node.expression

import doge.ast.location.SourceLocation
import doge.ast.node.expression.operation.BinaryOperation
import doge.visitor.QuestionnaireASTBaseVisitor

class BinaryExpression(
        val left: Expression,
        val right: Expression,
        val operation: BinaryOperation,
        sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}