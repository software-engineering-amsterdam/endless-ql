package ui.view

import javafx.scene.control.CheckBox
import tornadofx.bind
import ui.model.QuestionModel

class QuestionCheckBox(question: QuestionModel) : CheckBox() {

    init {
        bind(question.booleanValue)

        selectedProperty().addListener({ _, _, _ ->
            question.update()
        })
    }
}