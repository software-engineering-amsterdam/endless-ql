package ui.view

import javafx.beans.property.StringProperty
import javafx.scene.control.TextField
import javafx.scene.control.TextInputControl
import tornadofx.mutateOnChange
import ui.model.QuestionViewModel

abstract class QuestionTextField(val question: QuestionViewModel) : QuestionField() {

    final override val root = TextField()

    init {
        with(root) {
            isDisable = question.readOnly
        }
    }

    // Listener must be attached after binding model
    // Or else it will be called before the model is updated and validated
    fun attachListener(textProperty: StringProperty) {
        textProperty.addListener({ _, _, _ ->
            question.update()
        })
    }

    internal fun TextInputControl.stripNonNumeric(vararg allowedChars: String = arrayOf(".", ",", "-")) = textProperty().mutateOnChange {
        it?.replace(Regex("[^0-9${allowedChars.joinToString("")}]"), "")
    }
}