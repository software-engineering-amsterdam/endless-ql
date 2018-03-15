package node

import data.question.Question
import data.symbol.SymbolTable
import typechecker.pass.ScopePass

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

}