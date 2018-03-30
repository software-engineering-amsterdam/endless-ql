package ui.view

import javafx.scene.control.TextField
import javafx.scene.control.TextInputControl
import tornadofx.mutateOnChange
import ui.model.QuestionViewModel

abstract class QuestionTextField(question: QuestionViewModel) : QuestionField() {

    final override val root = TextField()

    init {
        with(root) {
            textProperty().addListener({ _, _, newValue ->
                if (newValue.isNotBlank()) {
                    question.update()
                }
            })

            isDisable = question.readOnly
        }
    }

    internal fun TextInputControl.stripNonNumeric(vararg allowedChars: String = arrayOf(".", ",", "-")) = textProperty().mutateOnChange {
        it?.replace(Regex("[^0-9${allowedChars.joinToString("")}]"), "")
    }
}