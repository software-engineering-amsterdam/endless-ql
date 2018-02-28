package ui

import data.value.BooleanValue
import data.value.IntegerValue
import data.question.Question
import tornadofx.Controller

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        return listOf(Question("Question 1?", IntegerValue(1)),
                Question("Question 2?", IntegerValue(2)),
                Question("Question 3?", BooleanValue(true)))
    }

}