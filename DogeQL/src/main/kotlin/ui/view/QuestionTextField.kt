package ui.view

import data.question.SymbolType
import javafx.scene.control.TextField
import javafx.util.converter.BigDecimalStringConverter
import tornadofx.*
import ui.model.QuestionModel

class QuestionTextField(question: QuestionModel) : TextField() {
    init {
        when (question.item.value.type) {
            SymbolType.INTEGER -> {
                bind(question.integerValue)
                stripNonInteger()
            }
            SymbolType.DECIMAL -> {
                bind(question.decimalValue)
                stripNonNumeric()
                BigDecimalStringConverter()
            }
            SymbolType.MONEY -> {
                bind(question.moneyValue)
                stripNonNumeric()
                BigDecimalStringConverter()
            }
            SymbolType.STRING -> {
                bind(question.stringValue)
                stripWhitespace()
            }
            else -> throw IllegalArgumentException("unsupported type")
        }

        validator {
            question.validate()
        }
    }
}