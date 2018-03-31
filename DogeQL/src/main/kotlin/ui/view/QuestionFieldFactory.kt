package ui.view

import ui.model.*
import ui.view.field.*

class QuestionFieldFactory {

    fun createQuestionField(question : QuestionViewModel) : QuestionField {
        return when(question){
            is StringViewModel -> StringField(question)
            is MoneyViewModel -> MoneyField(question)
            is IntegerViewModel -> IntegerField(question)
            is DecimalViewModel -> DecimalField(question)
            is BooleanViewModel -> CheckBox(question)
            is DateViewModel -> DateField(question)
            else -> throw IllegalArgumentException("${question.item.value.type} unsupported type")
        }
    }
}