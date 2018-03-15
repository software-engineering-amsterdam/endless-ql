package node

import data.question.Question
import data.symbol.SymbolTable

class RootNode(symbolTable: SymbolTable) : Node(symbolTable) {
    override fun getEnabledQuestions(): List<Question> = children.flatMap { child ->
        child.getEnabledQuestions()
    }
}