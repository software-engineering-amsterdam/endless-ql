package expression

class BinaryExpression(
        private val left: BinaryExpression,
        private val right: BinaryExpression,
        private val operation: BinaryOperation
) : Expression {

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(left)
        visitor.visit(right)
    }

}