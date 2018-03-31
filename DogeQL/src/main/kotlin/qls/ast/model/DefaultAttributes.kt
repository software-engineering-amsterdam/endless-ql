package qls.ast.model

import qls.visitor.QlsVisitor

data class DefaultAttributes(val type: String) : Element {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}