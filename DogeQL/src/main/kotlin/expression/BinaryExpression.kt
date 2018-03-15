package expression

import data.value.BaseSymbolValue
import expression.operation.BinaryOperation
import expression.visitor.evaluation.EvaluationVisitor
import expression.visitor.reference.ReferenceCollector
import expression.visitor.reference.ReferenceVisitor

class BinaryExpression(val left: Expression, val right: Expression, val operation: BinaryOperation) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return visitor.visit(this)
    }

    override fun accept(visitor: ReferenceVisitor): Boolean {
        return visitor.visit(this)
    }

    override fun accept(visitor: ReferenceCollector) {
        return visitor.visit(this)
    }

}