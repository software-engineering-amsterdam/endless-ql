package expression

import data.value.BaseSymbolValue
import expression.operation.UnaryOperation
import expression.visitor.evaluation.EvaluationVisitor
import expression.visitor.reference.ReferenceVisitor

class UnaryExpression(val next: Expression, val operation: UnaryOperation) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return visitor.visit(this)
    }

    override fun accept(visitor: ReferenceVisitor): Boolean {
        return visitor.visit(this)
    }

}