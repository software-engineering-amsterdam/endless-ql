package ui.view

import data.question.SymbolType
import javafx.scene.control.TextField
import javafx.scene.control.TextInputControl
import javafx.util.converter.BigDecimalStringConverter
import tornadofx.bind
import tornadofx.mutateOnChange
import tornadofx.stripWhitespace
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
            else -> throw IllegalArgumentException("${question.item.value.type} unsupported type")
        }

        textProperty().addListener({ _, _, newValue ->
            if (newValue.isNotBlank()) {
                question.update()
            }
        })
    }

    private fun TextInputControl.stripNonNumeric(vararg allowedChars: String = arrayOf(".", ",", "-")) = textProperty().mutateOnChange {
        it?.replace(Regex("[^0-9${allowedChars.joinToString("")}]"), "")
    }

    private fun TextInputControl.stripNonInteger() = textProperty().mutateOnChange { it?.replace(Regex("[^0-9\\-]"), "") }

}