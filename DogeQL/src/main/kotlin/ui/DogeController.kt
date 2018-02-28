package ui

import data.IntegerValue
import data.Question
import tornadofx.Controller

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        return listOf(Question("Question 1?", IntegerValue(1)),
                Question("Question 2?", IntegerValue(2)))
    }

}