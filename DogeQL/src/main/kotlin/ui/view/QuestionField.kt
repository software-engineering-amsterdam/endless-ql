package ui.view

import data.question.SymbolType
import tornadofx.View
import tornadofx.field
import ui.model.QuestionModel

class QuestionField(question: QuestionModel) : View(){

    override val root = field ()

    init{
        with(root){
            when(question.item.value.type) {
                SymbolType.Boolean-> add(QuestionCheckBox(question))
                else -> add(QuestionTextField(question))
            }
        }
    }

}