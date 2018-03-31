package qls.ast.model

import qls.visitor.QlsVisitor

data class Section(val title: String, val elements: List<Element>) : Element, Style {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}