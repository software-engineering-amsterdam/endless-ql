package node

import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.ScopePass

class RootNode(symbolTable: SymbolTable) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> = children.flatMap { child ->
        child.getEnabledQuestions()
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }

}