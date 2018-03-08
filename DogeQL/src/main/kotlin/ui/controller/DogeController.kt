package ui.controller

import data.question.Question
import data.value.*
import tornadofx.Controller
import java.math.BigDecimal

class DogeController: Controller() {

    fun getQuestions(): List<Question> {
        return listOf(
                Question("Question Int 1?", IntegerValue(1)),
                Question("Question Str 2?", StringValue("Hoi")),
                Question("Question  Money 3?", MoneyValue(BigDecimal(10))),
                Question("Question BigDec 4?", DecimalValue(BigDecimal(23))),
                Question("Question Bool 5?", BooleanValue(false)))
    }

}