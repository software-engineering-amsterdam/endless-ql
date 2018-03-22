package doge.expression

import doge.data.question.SymbolType
import doge.data.value.BaseSymbolValue
import doge.expression.operation.BinaryOperation
import doge.expression.visitor.evaluation.EvaluationVisitor
import doge.expression.visitor.evaluation.TypeVisitor
import doge.expression.visitor.reference.ReferenceCollector
import doge.expression.visitor.reference.ReferenceVisitor

class BinaryExpression(
        val left: Expression, val right: Expression, val operation: BinaryOperation, sourceLocation: SourceLocation
) : Expression(sourceLocation) {

    override fun accept(visitor: EvaluationVisitor): BaseSymbolValue {
        return visitor.visit(this)
    }

    override fun accept(visitor: TypeVisitor): SymbolType {
        return visitor.visit(this)
    }

    override fun accept(visitor: ReferenceVisitor): Boolean {
        return visitor.visit(this)
    }

    override fun accept(visitor: ReferenceCollector) {
        return visitor.visit(this)
    }

}