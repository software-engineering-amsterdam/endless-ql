package doge.expression.visitor.reference

import doge.expression.BinaryExpression
import doge.expression.LiteralExpression
import doge.expression.ReferenceExpression
import doge.expression.UnaryExpression
import doge.expression.visitor.ExpressionVisitor

class ReferenceCollector : ExpressionVisitor<Unit> {

    val references = mutableListOf<ReferenceExpression>()

    override fun visit(literal: LiteralExpression) {

    }

    override fun visit(reference: ReferenceExpression) {
        references.add(reference)
    }

    override fun visit(unary: UnaryExpression) {
        unary.next.accept(this)
    }

    override fun visit(binary: BinaryExpression) {
        binary.left.accept(this)
        binary.right.accept(this)
    }

}