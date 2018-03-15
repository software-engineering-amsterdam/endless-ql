package node

import data.question.Question
import data.symbol.SymbolTable

class QuestionNode(symbolTable: SymbolTable, val question: Question) : Node(symbolTable) {
    override fun getEnabledQuestions(): List<Question> {
        question.update(symbolTable)

        return listOf(question)
    }
}