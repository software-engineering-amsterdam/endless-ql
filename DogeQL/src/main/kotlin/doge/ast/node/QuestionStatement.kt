package doge.ast.node

import doge.ast.location.Identifier
import doge.ast.location.SourceLocation
import doge.ast.location.Type
import doge.ast.node.expression.Expression
import doge.visitor.QuestionnaireASTBaseVisitor

class QuestionStatement(
        val label: Identifier,
        val name: Identifier,
        val type: Type,
        val expression: Expression?,
        location: SourceLocation
) : Statement(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}