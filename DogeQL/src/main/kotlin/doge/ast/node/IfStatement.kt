package doge.ast.node

import doge.ast.location.SourceLocation
import doge.ast.node.expression.Expression
import doge.visitor.QuestionnaireASTBaseVisitor

class IfStatement(val expression: Expression, val block: Block, location: SourceLocation) : Statement(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}