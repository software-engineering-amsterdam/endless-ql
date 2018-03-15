package node

import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.ScopePass

class QuestionNode(symbolTable: SymbolTable, val question: Question) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {
        question.update(symbolTable)

        return listOf(question)
    }

    override fun accept(pass: ScopePass) {
        pass.visit(this)
    }

}