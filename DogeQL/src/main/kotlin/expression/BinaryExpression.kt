package expression

import data.value.BaseSymbolValue
import expression.operation.BinaryOperation
import expression.visitor.evaluation.EvaluationVisitor

class BinaryExpression(
        private val left: BinaryExpression,
        private val right: BinaryExpression,
        private val operation: BinaryOperation
) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return operation(visitor.visit(left), visitor.visit(right))
    }

}