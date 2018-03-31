package ui.model

import ui.model.domain.Question
import javafx.beans.property.SimpleObjectProperty
import java.math.BigDecimal

class DecimalViewModel(question: Question) : QuestionViewModel(question){

    var decimalProperty = bind { SimpleObjectProperty<BigDecimal>(item.value.decimalValue.value) }

    override fun setViewModelValue(question: Question) {
        decimalProperty.value = question.value.decimalValue.value
    }

    override fun synchronizeDataModel() {
        item.value.decimalValue.value = decimalProperty.value
    }

}