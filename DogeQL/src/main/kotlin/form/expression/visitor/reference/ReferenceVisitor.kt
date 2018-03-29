package form.expression.visitor.reference

import form.expression.BinaryExpression
import form.expression.LiteralExpression
import form.expression.ReferenceExpression
import form.expression.UnaryExpression
import form.expression.visitor.ExpressionVisitor

class ReferenceVisitor : ExpressionVisitor<Boolean> {

    override fun visit(literal: LiteralExpression): Boolean {
        return true
    }

    override fun visit(reference: ReferenceExpression): Boolean {
        return false
    }

    override fun visit(unary: UnaryExpression): Boolean {
        return unary.next.accept(this)
    }

    override fun visit(binary: BinaryExpression): Boolean {
        return binary.left.accept(this) && binary.right.accept(this)
    }

}