package doge.ast.node

import doge.ast.location.SourceLocation
import doge.visitor.QuestionnaireASTBaseVisitor

class Block(val statements: List<Statement>, location: SourceLocation) : QLNode(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}