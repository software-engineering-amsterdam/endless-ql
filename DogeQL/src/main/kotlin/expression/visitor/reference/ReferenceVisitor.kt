package expression.visitor.reference

import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor

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