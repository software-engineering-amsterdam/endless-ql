package ui

import data.question.Question
import data.value.IntegerValue
import data.value.StringValue
import tornadofx.Controller

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        return listOf(Question("Question 1?", IntegerValue(1)),
                Question("Question 2?", StringValue("Hoi")))
    }

}