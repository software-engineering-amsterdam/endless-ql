package ui.view

import tornadofx.bind
import tornadofx.checkbox
import ui.model.BooleanViewModel

class CheckBox(question: BooleanViewModel) : QuestionField() {

    override val root = checkbox {
        bind(question.booleanValue)

        selectedProperty().addListener({ _, _, _ ->
            question.update()
        })
    }
}