package doge.expression

import doge.data.question.SymbolType
import doge.data.symbol.SymbolTable
import doge.data.value.BaseSymbolValue
import doge.expression.visitor.evaluation.EvaluationVisitor
import doge.expression.visitor.evaluation.TypeVisitor
import doge.expression.visitor.reference.ReferenceCollector
import doge.expression.visitor.reference.ReferenceVisitor

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
