package qls.ast.model

import qls.ast.node.QlsNode
import qls.visitor.QlsVisitor

data class Page(val styles: List<Style>, val title: String) : QlsNode {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}