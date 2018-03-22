package form.expression.visitor.reference

import form.expression.BinaryExpression
import form.expression.LiteralExpression
import form.expression.ReferenceExpression
import form.expression.UnaryExpression
import form.expression.visitor.ExpressionVisitor

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