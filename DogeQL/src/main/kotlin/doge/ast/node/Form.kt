package doge.ast.node

import doge.ast.location.Identifier
import doge.ast.location.SourceLocation
import doge.visitor.QuestionnaireASTBaseVisitor

class Form(val name: Identifier, val block: Block, location: SourceLocation) : QLNode(location) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}