package expression

import data.BaseSymbolValue
import expression.operation.UnaryOperation
import expression.visitor.evaluation.EvaluationVisitor

class UnaryExpression(val next: Expression, private val operation: UnaryOperation) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return operation(visitor.visit(next))
    }

}