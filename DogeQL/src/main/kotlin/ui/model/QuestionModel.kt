package ui.model

import data.question.Question
import data.question.SymbolType
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.ValidationMessage
import ui.controller.DogeController
import java.math.BigDecimal

class QuestionModel(question: Question) : ItemViewModel<Question>(question) {

    var integerValue = SimpleIntegerProperty()
    var booleanValue = SimpleBooleanProperty()
    var stringValue = SimpleStringProperty()
    var decimalValue = SimpleObjectProperty<BigDecimal>()
    var moneyValue = SimpleObjectProperty<BigDecimal>()

    private val dogeController: DogeController by inject()
    private val model: QuestionFormModel by inject()



    init {
        when (item.value.type) {
            SymbolType.INTEGER -> integerValue = bind { SimpleIntegerProperty(item.value.integerValue.value) }
            SymbolType.Boolean -> booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }
            SymbolType.STRING -> stringValue = bind { SimpleStringProperty(item.value.stringValue.value) }
            SymbolType.DECIMAL -> decimalValue = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }
            SymbolType.MONEY -> moneyValue = bind { SimpleObjectProperty<BigDecimal>(item.value.moneyValue.value) }
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    override fun onCommit() {
        updateDataModel()
    }

    fun validate() : ValidationMessage? {
        updateDataModel()
        dogeController.updateQuestion(item)
//        model.load()
        return null
    }

    fun updateDataModel(){
        when (item.value.type) {
            SymbolType.STRING -> item.value.stringValue.value = stringValue.value
            SymbolType.Boolean -> item.value.booleanValue.value = booleanValue.value
            SymbolType.INTEGER -> item.value.integerValue.value = integerValue.value
            SymbolType.DECIMAL -> item.value.decimalValue.value = decimalValue.value
            SymbolType.MONEY -> item.value.moneyValue.value = moneyValue.value
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}


