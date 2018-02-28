package ui.question

import data.BaseSymbolValue
import data.Question
import data.QuestionType
import javafx.beans.property.*
import tornadofx.ItemViewModel

class QuestionViewModel(question : Question) : ItemViewModel<Question>(question){

    var label = bind{ SimpleStringProperty(item?.label) }

    var value = bind { SimpleObjectProperty<BaseSymbolValue>(item.value)}

    var boolValue = bind { SimpleBooleanProperty(item.value.booleanValue.value) }

    var integerValue = bind { SimpleIntegerProperty(item.value.integerValue.value) }

}


