package expression.visitor.reference

import common.Name
import expression.BinaryExpression
import expression.LiteralExpression
import expression.ReferenceExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor

class ReferenceCollector : ExpressionVisitor<Unit> {

    val references = mutableListOf<Name>()

    override fun visit(literal: LiteralExpression) {

    }

    override fun visit(reference: ReferenceExpression) {
        references.add(reference.name)
    }

    override fun visit(unary: UnaryExpression) {
        unary.next.accept(this)
    }

    override fun visit(binary: BinaryExpression) {
        binary.left.accept(this)
        binary.right.accept(this)
    }

}