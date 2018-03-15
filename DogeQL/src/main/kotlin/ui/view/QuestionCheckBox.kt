package ui.view

import javafx.scene.control.CheckBox
import tornadofx.bind
import tornadofx.validator
import ui.model.QuestionModel

class QuestionCheckBox(question : QuestionModel) : CheckBox(){

    init {
        bind(question.booleanValue)
        validator {
            _: Boolean? -> question.validate()
        }
    }
}