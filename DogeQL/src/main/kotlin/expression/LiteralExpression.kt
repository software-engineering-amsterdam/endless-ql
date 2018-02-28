package expression

import data.value.BaseSymbolValue
import expression.visitor.evaluation.EvaluationVisitor

class LiteralExpression(private val value: BaseSymbolValue): Expression {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return value
    }

}