package form.ui.controller

import form.ast.DogeParser
import form.data.question.Question
import tornadofx.Controller

class DogeController: Controller() {

    private val tree = DogeParser().parse()

    fun getQuestions(): List<Question> {
        tree.symbolTable.evaluateTable()
        return tree.getEnabledQuestions()
    }

    fun updateQuestion(question: Question){
        tree.updateQuestion(question)
    }
}