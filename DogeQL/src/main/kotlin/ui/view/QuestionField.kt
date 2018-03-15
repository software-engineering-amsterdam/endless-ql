package ui.view

import data.question.SymbolType
import tornadofx.View
import tornadofx.field
import tornadofx.hbox
import ui.model.QuestionModel

class QuestionField(question: QuestionModel) : View(){

    override val root = hbox ()

    init{
        with(root){
            when(question.item.value.type) {
                SymbolType.BOOLEAN -> add(QuestionCheckBox(question))
                else -> add(QuestionTextField(question))
            }
        }
    }

}