package form.node

import form.common.Name
import form.data.question.Question
import form.data.question.SymbolType
import form.data.symbol.SymbolTable
import form.expression.SourceLocation
import form.typechecker.pass.CircularDependencyPass
import form.typechecker.pass.DuplicatePass
import form.typechecker.pass.ScopePass
import form.typechecker.pass.TypePass

class ExpressionNode(symbolTable: SymbolTable, val reference: Name, val sourceLocation: SourceLocation)
    : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {

        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            throw IllegalStateException("TODO")
        }

        val castedValue = symbol.value.castTo(SymbolType.BOOLEAN)
                ?: throw IllegalStateException("Unable to cast to boolean") // TODO: better exception

        if (castedValue.booleanValue.value) {
            return children.flatMap { child ->
                child.getEnabledQuestions()
            }
        }
        return listOf()
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }

    override fun accept(pass: DuplicatePass) {
        pass.visit(this)
    }

    override fun accept(pass: TypePass) {
        pass.visit(this)
    }

    override fun accept(pass: CircularDependencyPass) {
        pass.visit(this)
    }
}