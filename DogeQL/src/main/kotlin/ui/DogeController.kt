package ui

import data.BooleanValue
import data.Question
import tornadofx.Controller

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        return listOf(Question("Hey?", BooleanValue(true)))
    }

}