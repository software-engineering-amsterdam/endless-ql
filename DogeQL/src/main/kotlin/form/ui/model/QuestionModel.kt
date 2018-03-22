package form.ui.model

import form.data.question.Question
import form.data.question.SymbolType
import form.ui.controller.DogeController
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import java.math.BigDecimal

class QuestionModel(question: Question) : ItemViewModel<Question>(question) {

    var integerValue = SimpleIntegerProperty()
    var booleanValue = SimpleBooleanProperty()
    var stringValue = SimpleStringProperty()
    var decimalValue = SimpleObjectProperty<BigDecimal>()
    var moneyValue = SimpleObjectProperty<BigDecimal>()

    var readOnly: Boolean = false

    private val dogeController: DogeController by inject()
    private val questionFormModel: QuestionFormModel by inject()

    init {
        when (item.value.type) {
            SymbolType.INTEGER -> integerValue = bind { SimpleIntegerProperty(item.value.integerValue.value) }
            SymbolType.BOOLEAN -> booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }
            SymbolType.STRING -> stringValue = bind { SimpleStringProperty(item.value.stringValue.value) }
            SymbolType.DECIMAL -> decimalValue = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }
            SymbolType.MONEY -> moneyValue = bind { SimpleObjectProperty<BigDecimal>(item.value.moneyValue.value) }
            else -> throw IllegalArgumentException("${item.value.type} unsupported type")
        }


    }

    override fun onCommit() {
        synchronizeDataModel()
    }

    fun update(){
        // Only update if there are changes
        if (dirtyProperties.size > 0) {
            synchronizeDataModel()
            dogeController.updateQuestion(item)
            questionFormModel.load()
        }
    }

    fun setValue(question: Question, type: SymbolType) = when (type) {
        SymbolType.STRING -> stringValue.value = question.value.stringValue.value
        SymbolType.BOOLEAN -> booleanValue.value = question.value.booleanValue.value
        SymbolType.INTEGER -> integerValue.value = question.value.integerValue.value
        SymbolType.DECIMAL -> decimalValue.value = question.value.decimalValue.value
        SymbolType.MONEY -> moneyValue.value = question.value.moneyValue.value
        else -> throw IllegalArgumentException("${item.value.type} unsupported type")
    }

    private fun synchronizeDataModel() = when (item.value.type) {
        SymbolType.STRING -> item.value.stringValue.value = stringValue.value
        SymbolType.BOOLEAN -> item.value.booleanValue.value = booleanValue.value
        SymbolType.INTEGER -> item.value.integerValue.value = integerValue.value
        SymbolType.DECIMAL -> item.value.decimalValue.value = decimalValue.value
        SymbolType.MONEY -> item.value.moneyValue.value = moneyValue.value
        else -> throw IllegalArgumentException("${item.value.type} unsupported type")
    }
}


