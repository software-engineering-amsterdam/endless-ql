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
                stripNonInteger()
                bind(question.integerValue)
            }
            SymbolType.DECIMAL -> {
                stripNonNumeric()
                BigDecimalStringConverter()
                bind(question.decimalValue)
            }
            SymbolType.MONEY -> {
                stripNonNumeric()
                BigDecimalStringConverter()
                bind(question.moneyValue)
            }
            SymbolType.STRING -> {
                stripWhitespace()
                bind(question.stringValue)
            }
            else -> throw IllegalArgumentException("unsupported type")
        }

        textProperty().addListener({ _, _, _ ->
            question.update()
        })
    }
}