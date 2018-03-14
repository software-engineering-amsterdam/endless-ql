package ui.model

import data.question.Question
import data.question.SymbolType
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
            SymbolType.Integer -> integerValue = bind { SimpleIntegerProperty(item.value.integerValue.value) }
            SymbolType.Boolean -> booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }
            SymbolType.String -> stringValue = bind { SimpleStringProperty(item.value.stringValue.value) }
            SymbolType.Decimal -> decimalValue = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }
            SymbolType.Money -> moneyValue = bind { SimpleObjectProperty<BigDecimal>(item.value.moneyValue.value) }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun onCommit() {
        when (item.value.type) {
            SymbolType.String -> item.value.stringValue.value = stringValue.value
            SymbolType.Boolean -> item.value.booleanValue.value = booleanValue.value
            SymbolType.Integer -> item.value.integerValue.value = integerValue.value
            SymbolType.Decimal -> item.value.decimalValue.value = decimalValue.value
            SymbolType.Money -> item.value.moneyValue.value = moneyValue.value
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    fun validate() : ValidationMessage? {
        return null
    }
}


