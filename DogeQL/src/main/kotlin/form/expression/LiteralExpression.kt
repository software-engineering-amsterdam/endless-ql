package form.expression

import form.data.question.SymbolType
import form.data.value.BaseSymbolValue
import form.expression.visitor.evaluation.EvaluationVisitor
import form.expression.visitor.evaluation.TypeVisitor
import form.expression.visitor.reference.ReferenceCollector
import form.expression.visitor.reference.ReferenceVisitor

class LiteralExpression(val value: BaseSymbolValue, sourceLocation: SourceLocation) : Expression(sourceLocation) {

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