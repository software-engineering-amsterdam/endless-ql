package ui.controller

import ast.DogeParser
import data.question.Question
import tornadofx.Controller

class DogeController: Controller() {

    private val tree = DogeParser().parse()

    fun getQuestions(): List<Question> {
        return tree.getEnabledQuestions()
    }

    fun updateQuestion(question: Question){
        tree.updateQuestion(question)
    }
}