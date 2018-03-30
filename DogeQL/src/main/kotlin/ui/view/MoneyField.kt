package ui.view

import ui.model.MoneyViewModel
import tornadofx.bind

class MoneyField(question: MoneyViewModel) : QuestionTextField(question) {
    init {
        with(root){
            stripNonNumeric()
            bind(question.moneyProperty)
            attachListener(textProperty())
        }
    }
}