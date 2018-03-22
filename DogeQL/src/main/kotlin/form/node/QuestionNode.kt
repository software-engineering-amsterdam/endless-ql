package form.node

import form.data.question.Question
import form.data.symbol.SymbolTable
import form.typechecker.pass.CircularDependencyPass
import form.typechecker.pass.DuplicatePass
import form.typechecker.pass.ScopePass
import form.typechecker.pass.TypePass

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

    override fun accept(pass: CircularDependencyPass) {
        pass.visit(this)
    }
}