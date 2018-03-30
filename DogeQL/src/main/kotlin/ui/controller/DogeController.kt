package ui.controller

import doge.ast.DogeParser
import doge.data.question.Question
import qls.ast.QlsParser
import tornadofx.Controller

class DogeController: Controller() {

    private val tree = DogeParser().parse()
    private val qlsTree = QlsParser().parse()

    fun getQuestions(): List<Question> {
        return tree
    }

    fun updateQuestion(question: Question){
    }
}