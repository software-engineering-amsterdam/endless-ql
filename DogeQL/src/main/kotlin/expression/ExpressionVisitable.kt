package expression

interface ExpressionVisitable {

    fun accept(visitor: ExpressionVisitor)

}