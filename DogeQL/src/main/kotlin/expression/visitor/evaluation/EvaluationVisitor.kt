package expression.visitor.evaluation

import data.value.BaseSymbolValue
import expression.BinaryExpression
import expression.Expression
import expression.LiteralExpression
import expression.UnaryExpression
import expression.visitor.ExpressionVisitor


class EvaluationVisitor: ExpressionVisitor<BaseSymbolValue> {

    override fun visit(expression: Expression): BaseSymbolValue {
        throw UnsupportedOperationException("Unable to evaluate expression $expression")
    }

    override fun visit(literal: LiteralExpression): BaseSymbolValue {
        return literal.value
    }

    override fun visit(unary: UnaryExpression): BaseSymbolValue {
        return unary.operation(unary.next.accept(this))
    }

    override fun visit(binary: BinaryExpression): BaseSymbolValue {
        return binary.operation(binary.left.accept(this), binary.right.accept(this))
    }

}