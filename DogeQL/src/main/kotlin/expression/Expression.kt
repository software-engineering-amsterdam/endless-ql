package expression

import common.Name
import data.question.SymbolType
import data.symbol.SymbolTable
import data.value.BaseSymbolValue
import expression.visitor.evaluation.EvaluationVisitor
import expression.visitor.evaluation.TypeVisitor
import expression.visitor.reference.ReferenceCollector
import expression.visitor.reference.ReferenceVisitor

interface Expression {

    fun accept(visitor: EvaluationVisitor): BaseSymbolValue

    fun accept(visitor: TypeVisitor): SymbolType

    fun accept(visitor: ReferenceVisitor): Boolean

    fun accept(visitor: ReferenceCollector)

    fun containsReference(): Boolean {
        return !accept(ReferenceVisitor())
    }

    fun evaluate(symbolTable: SymbolTable): BaseSymbolValue {
        return accept(EvaluationVisitor(symbolTable))
    }

    fun determineType(symbolTable: SymbolTable): SymbolType {
        return accept(TypeVisitor(symbolTable))
    }

    fun allReferences(): List<Name> {
        val collector = ReferenceCollector()
        accept(collector)

        return collector.references
    }
}
