package ui.model

import data.question.Question
import data.question.QuestionType
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.ValidationMessage
import tornadofx.ValidationSeverity
import java.math.BigDecimal

class QuestionModel(question: Question) : ItemViewModel<Question>(question) {

    var integerValue = SimpleIntegerProperty()
    var booleanValue = SimpleBooleanProperty()
    var stringValue = SimpleStringProperty()
    var decimalValue = SimpleObjectProperty<BigDecimal>()
    var moneyValue = SimpleObjectProperty<BigDecimal>()

    init {
        when (item.value.type) {
            QuestionType.INTEGER -> integerValue = bind { SimpleIntegerProperty(item.value.integerValue.value) }
            QuestionType.BOOLEAN -> booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }
            QuestionType.STRING -> stringValue = bind { SimpleStringProperty(item.value.stringValue.value) }
            QuestionType.DECIMAL -> decimalValue = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }
            QuestionType.MONEY -> moneyValue = bind { SimpleObjectProperty<BigDecimal>(item.value.moneyValue.value) }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun onCommit() {
        when (item.value.type) {
            QuestionType.STRING -> item.value.stringValue.value = stringValue.value
            QuestionType.BOOLEAN -> item.value.booleanValue.value = booleanValue.value
            QuestionType.INTEGER -> item.value.integerValue.value = integerValue.value
            QuestionType.DECIMAL -> item.value.decimalValue.value = decimalValue.value
            QuestionType.MONEY -> item.value.moneyValue.value = moneyValue.value
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun validate() : ValidationMessage? {
        return null
    }
}


