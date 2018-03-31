package qls.ast.model

import qls.visitor.QlsVisitor

class Widget(val type: WidgetType) : Attribute {
    override fun <T> accept(visitor: QlsVisitor<T>): T {
        return visitor.visit(this)
    }
}