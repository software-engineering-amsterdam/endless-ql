package ui.controller

import ast.DogeParser
import data.question.Question
import data.value.*
import node.Node
import tornadofx.Controller
import java.math.BigDecimal

class DogeController: Controller() {

//    fun getQuestions(): Node {
//        val parser = DogeParser()
//        return parser.parse()
//    }

    fun getQuestions(): List<Question> {
        return listOf(
                Question("q1", "Question Int 1?", IntegerValue(1)),
                Question("q2","Question Str 2?", StringValue("Hoi")),
                Question("q3","Question  Money 3?", MoneyValue(BigDecimal(10))),
                Question("q4","Question BigDec 4?", DecimalValue(BigDecimal(23))),
                Question("q5","Question Bool 5?", BooleanValue(false)))
    }

}