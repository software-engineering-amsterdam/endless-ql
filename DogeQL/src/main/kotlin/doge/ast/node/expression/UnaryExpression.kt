package doge.ast.node.expression

import doge.ast.location.SourceLocation
import doge.ast.node.expression.operation.UnaryOperation
import doge.visitor.QuestionnaireASTBaseVisitor

class UnaryExpression(val next: Expression, val operation: UnaryOperation, sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}