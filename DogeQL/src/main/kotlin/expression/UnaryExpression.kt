package expression

class UnaryExpression(val next: Expression, val operation: UnaryOperation) : Expression {

    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(next)
    }

}