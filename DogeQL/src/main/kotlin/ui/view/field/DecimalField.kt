package ui.view.field

import ui.model.DecimalViewModel
import tornadofx.bind

class DecimalField(question: DecimalViewModel) : QuestionTextField(question) {
    init {
        with(root) {
            stripNonNumeric()
            bind(question.decimalProperty)
            attachListener(textProperty())
        }
    }
}