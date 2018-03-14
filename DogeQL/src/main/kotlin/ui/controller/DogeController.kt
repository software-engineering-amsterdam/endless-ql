package ui.controller

import ast.DogeParser
import data.question.Question
import tornadofx.Controller

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        val tree = DogeParser().parse()
        return tree.getEnabledQuestions()
    }
}