package ui.view

import data.question.QuestionType
import tornadofx.View
import tornadofx.field
import ui.model.QuestionModel

class QuestionField(question: QuestionModel) : View(){

    override val root = field ()

    init{
        with(root){
            when(question.item.value.type) {
                QuestionType.BOOLEAN-> add(QuestionCheckBox(question))
                else -> add(QuestionTextField(question))
            }
        }
    }

}