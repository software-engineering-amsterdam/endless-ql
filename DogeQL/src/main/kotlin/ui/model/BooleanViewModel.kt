package ui.model

import ui.model.domain.Question
import javafx.beans.property.SimpleBooleanProperty

class BooleanViewModel(question: Question) : QuestionViewModel(question) {

    var booleanValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }

    override fun setViewModelValue(question: Question) {
        booleanValue.value = question.value.booleanValue.value
    }

    override fun synchronizeDataModel()  {
        item.value.booleanValue.value = booleanValue.value
    }

}