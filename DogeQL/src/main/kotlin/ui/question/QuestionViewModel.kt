package ui.question

import data.question.Question
import data.value.IntegerValue
import data.value.StringValue
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

class QuestionViewModel(question : Question) : ItemViewModel<Question>(question){

    var label = bind{ SimpleStringProperty(question.label) }

    var value = when(question.value){
        is StringValue -> bind{ SimpleStringProperty(item.value.stringValue.value) }
        is IntegerValue -> bind{ SimpleIntegerProperty(item.value.integerValue.value) }
        else -> throw IllegalArgumentException("Unsupported type")}

}


