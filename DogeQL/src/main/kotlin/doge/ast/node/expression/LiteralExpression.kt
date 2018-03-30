package doge.ast.node.expression

import doge.ast.location.SourceLocation
import doge.data.value.BaseSymbolValue
import doge.visitor.QuestionnaireASTBaseVisitor

class LiteralExpression(val value: BaseSymbolValue, sourceLocation: SourceLocation) : Expression(sourceLocation) {

    override fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T {
        return visitor.visit(this)
    }

}