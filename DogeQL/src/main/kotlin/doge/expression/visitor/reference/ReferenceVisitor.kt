package doge.expression.visitor.reference

import doge.expression.BinaryExpression
import doge.expression.LiteralExpression
import doge.expression.ReferenceExpression
import doge.expression.UnaryExpression
import doge.expression.visitor.ExpressionVisitor

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