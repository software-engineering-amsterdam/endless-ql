package node

import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.CircularDependencyPass
import typechecker.pass.DuplicatePass
import typechecker.pass.ScopePass
import typechecker.pass.TypePass

class RootNode(symbolTable: SymbolTable) : Node(symbolTable) {

    override fun getEnabledQuestions(): List<Question> = children.flatMap { child ->
        child.getEnabledQuestions()
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