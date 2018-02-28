package ui.question

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.View
import tornadofx.field
import tornadofx.stripNonInteger
import tornadofx.textfield

class QuestionField(question: QuestionViewModel) : View(){

    override val root = field ()
    init{
        with(root){
            var value = question.value
            when(value) {
                is SimpleIntegerProperty ->textfield("Integer").stripNonInteger()
                is SimpleObjectProperty -> textfield("wtf")
                is SimpleStringProperty -> textfield("String ")
                else -> textfield(question.label)
            }
        }
    }

}