package qls.ast.model

import qls.ast.node.QlsNode
import qls.visitor.QlsVisitor

data class StyleSheet(val pages: List<Page>, val name: String) : QlsNode {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}