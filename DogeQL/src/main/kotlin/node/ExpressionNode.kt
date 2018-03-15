package node

import common.Name
import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.ScopePass

class ExpressionNode(symbolTable: SymbolTable, val reference: Name) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {

        val symbol = symbolTable.findSymbol(reference)

        if (symbol == null) {
            throw IllegalStateException("TODO")
        }

        if (symbol.value.booleanValue.value) {
            return children.flatMap { child ->
                child.getEnabledQuestions()
            }
        }
        return listOf()
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }
}