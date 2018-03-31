package qls.ast.model

import qls.ast.node.QlsNode
import qls.visitor.QlsVisitor

data class Question(val name : String) : Element, QlsNode {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}