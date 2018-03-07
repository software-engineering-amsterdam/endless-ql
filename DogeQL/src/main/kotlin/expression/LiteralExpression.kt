package expression

import data.value.BaseSymbolValue
import expression.visitor.evaluation.EvaluationVisitor

class LiteralExpression(val value: BaseSymbolValue) : Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return visitor.visit(this)
    }

}