package form.node

import form.data.question.Question
import form.data.symbol.SymbolTable
import form.typechecker.pass.CircularDependencyPass
import form.typechecker.pass.DuplicatePass
import form.typechecker.pass.ScopePass
import form.typechecker.pass.TypePass

abstract class Node(internal var symbolTable: SymbolTable) {
    internal val children = ArrayList<Node>()

    fun addChild(child: Node) {
        children.add(child)
    }

    open fun updateQuestion(question: Question) {
        symbolTable.assign(question.name, question.value)
        symbolTable.evaluateTable()
    }

    abstract fun getEnabledQuestions(): List<Question>

    abstract fun accept(pass: ScopePass)

    abstract fun accept(pass: DuplicatePass)

    abstract fun accept(pass: TypePass)

    abstract fun accept(pass: CircularDependencyPass)

}