package ui.model

import ui.model.domain.Question
import javafx.beans.property.SimpleIntegerProperty

class IntegerViewModel(question: Question) : QuestionViewModel(question) {

    var integerProperty = bind { SimpleIntegerProperty(item.value.integerValue.value) }

    override fun setViewModelValue(question: Question) {
        integerProperty.value = question.value.integerValue.value
    }

    override fun synchronizeDataModel() {
        item.value.integerValue.value = integerProperty.value.toInt()
    }

}