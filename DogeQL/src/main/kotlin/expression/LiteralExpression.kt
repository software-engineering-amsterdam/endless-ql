package expression

import data.BaseSymbolValue

class LiteralExpression(val value: BaseSymbolValue): Expression {

    override fun accept(visitor: ExpressionVisitor) {}

}