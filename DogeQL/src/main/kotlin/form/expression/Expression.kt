package form.expression

import form.data.question.SymbolType
import form.data.symbol.SymbolTable
import form.data.value.BaseSymbolValue
import form.expression.visitor.evaluation.EvaluationVisitor
import form.expression.visitor.evaluation.TypeVisitor
import form.expression.visitor.reference.ReferenceCollector
import form.expression.visitor.reference.ReferenceVisitor

abstract class Expression(val sourceLocation: SourceLocation) {

    abstract fun accept(visitor: EvaluationVisitor): BaseSymbolValue

    abstract fun accept(visitor: TypeVisitor): SymbolType

    abstract fun accept(visitor: ReferenceVisitor): Boolean

    abstract fun accept(visitor: ReferenceCollector)

    fun containsReference(): Boolean {
        return !accept(ReferenceVisitor())
    }

    fun evaluate(symbolTable: SymbolTable): BaseSymbolValue {
        return accept(EvaluationVisitor(symbolTable))
    }

    fun determineType(symbolTable: SymbolTable): SymbolType {
        return accept(TypeVisitor(symbolTable))
    }

    fun allReferences(): List<ReferenceExpression> {
        val collector = ReferenceCollector()
        accept(collector)

        return collector.references
    }
}
