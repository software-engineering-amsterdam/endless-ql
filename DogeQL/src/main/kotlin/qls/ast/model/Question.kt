package qls.ast.model

import qls.ast.node.QlsNode
import qls.common.Name
import qls.visitor.QlsVisitor

data class Question(val name : Name) : Element, QlsNode {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}