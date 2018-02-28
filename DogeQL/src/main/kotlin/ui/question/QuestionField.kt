package ui.question

import data.question.Question
import data.question.QuestionType
import tornadofx.*

class QuestionField(question: Question) : View(){

    override val root = field ()
    init{
        with(root){
            text = question.label
            when {
                question.value.type == QuestionType.BOOLEAN -> checkbox ()
                question.value.type == QuestionType.INTEGER -> textfield (question.value.integerValue.value.toString()).stripNonInteger()
                else -> textfield()
            }
        }
    }

}