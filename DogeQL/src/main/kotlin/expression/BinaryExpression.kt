package expression

import data.value.BaseSymbolValue
import expression.operation.BinaryOperation
import expression.visitor.evaluation.EvaluationVisitor

class BinaryExpression(
        private val left: Expression,
        private val right: Expression,
        private val operation: BinaryOperation
) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return operation(left.accept(visitor), right.accept(visitor))
    }

}