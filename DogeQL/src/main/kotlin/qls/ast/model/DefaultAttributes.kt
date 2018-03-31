package qls.ast.model

import qls.visitor.QlsVisitor

data class DefaultAttributes(val type: String, val attributes: List<Attribute>) : Element {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}