package form.ui.view

import form.ui.model.QuestionModel
import javafx.scene.control.CheckBox
import tornadofx.bind

class QuestionCheckBox(question: QuestionModel) : CheckBox() {

    init {
        bind(question.booleanValue)

        selectedProperty().addListener({ _, _, _ ->
            question.update()
        })
    }
}