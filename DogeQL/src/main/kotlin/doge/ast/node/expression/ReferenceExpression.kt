package doge.ast.node.expression

import doge.ast.location.Identifier
import doge.ast.location.SourceLocation
import doge.visitor.QuestionnaireASTBaseVisitor

class ReferenceExpression(val name: Identifier, sourceLocation: SourceLocation)
    : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}