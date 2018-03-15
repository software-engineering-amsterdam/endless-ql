package expression

import common.Name
import data.symbol.SymbolTable
import data.value.BaseSymbolValue
import expression.visitor.evaluation.EvaluationVisitor
import expression.visitor.reference.ReferenceCollector
import expression.visitor.reference.ReferenceVisitor

interface Expression {

    fun accept(visitor: EvaluationVisitor): BaseSymbolValue

    fun accept(visitor: ReferenceVisitor): Boolean

    fun accept(visitor: ReferenceCollector)

    fun containsReference(): Boolean {
        return !accept(ReferenceVisitor())
    }

    fun evaluate(symbolTable: SymbolTable): BaseSymbolValue {
        return accept(EvaluationVisitor(symbolTable))
    }

    fun allReferences(): List<Name> {
        val collector = ReferenceCollector()
        accept(collector)

        return collector.references
    }
}
