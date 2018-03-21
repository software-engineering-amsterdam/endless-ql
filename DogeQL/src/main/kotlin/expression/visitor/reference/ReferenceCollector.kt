package expression.visitor.reference

import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor

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