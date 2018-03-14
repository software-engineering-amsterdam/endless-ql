package ui.view

import data.question.QuestionType
import javafx.scene.control.TextField
import tornadofx.bind
import tornadofx.validator
import ui.model.QuestionModel

class QuestionTextField(question: QuestionModel) : TextField(){
    init {
        when(question.item.value.type) {
            QuestionType.INTEGER -> bind(question.integerValue)
            QuestionType.STRING -> bind(question.stringValue)
            QuestionType.DECIMAL-> bind(question.decimalValue)
            QuestionType.MONEY -> bind(question.moneyValue)
            else -> throw IllegalArgumentException("unsupported type")
        }

        validator {
            question.validate()
        }
    }
}