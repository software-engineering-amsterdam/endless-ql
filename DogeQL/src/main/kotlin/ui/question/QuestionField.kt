package ui.question

import data.BooleanValue
import data.QuestionType
import tornadofx.View
import tornadofx.checkbox
import tornadofx.field
import tornadofx.textfield

class QuestionField(question: QuestionViewModel) : View(){

    override val root = field ()
    init{
        with(root){
            when {
//                question.value.value is BooleanValue -> checkbox (question.value)
//                question.value.value.type == QuestionType.INTEGER -> textfield (question.value).stripNonInteger()
                else -> textfield(question.label)
            }
        }
    }

}