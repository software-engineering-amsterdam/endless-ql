package node

import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.DuplicatePass
import typechecker.pass.ScopePass
import typechecker.pass.TypePass

class QuestionNode(symbolTable: SymbolTable, val question: Question) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> {
        question.update(symbolTable)

        return listOf(question)
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

}