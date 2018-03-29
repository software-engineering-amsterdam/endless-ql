package doge.ast.node

import doge.ast.location.SourceLocation
import doge.visitor.QuestionnaireASTBaseVisitor

abstract class QLNode(val location: SourceLocation) {

    abstract fun <T> accept(visitor: QuestionnaireASTBaseVisitor<T>): T

}