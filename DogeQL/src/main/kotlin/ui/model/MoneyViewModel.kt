package ui.model

import ui.model.domain.Question
import javafx.beans.property.SimpleObjectProperty
import java.math.BigDecimal

class MoneyViewModel(question: Question) : QuestionViewModel(question) {

    var moneyProperty = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }

    override fun setViewModelValue(question: Question) {
        moneyProperty.value = question.value.decimalValue.value
    }

    override fun synchronizeDataModel() {
        item.value.decimalValue.value = moneyProperty.value
    }
}