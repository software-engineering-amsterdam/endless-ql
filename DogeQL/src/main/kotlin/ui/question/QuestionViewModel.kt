package ui.question

import data.value.BaseSymbolValue
import data.question.Question
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.observable
import ui.DogeController

class QuestionViewModel : ItemViewModel<Question>(){

    val dogeController : DogeController by inject()
    var questions = SimpleObjectProperty<ObservableList<Question>>()

    val label = bind{ SimpleStringProperty(item?.label) }
    val value = bind{ SimpleObjectProperty<BaseSymbolValue>(item?.value) }


    fun load (){
        runAsync {
            updateMessage("loading")
            dogeController.getQuestions().observable()
        } ui {
            questions.set(it)

        }
    }
}


