package ui.question

import data.Question
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.observable
import ui.DogeController

class FormViewModel : ItemViewModel<QuestionViewModel>(){

    var questions = FXCollections.observableArrayList<QuestionViewModel>()

    var dataQuestions = FXCollections.observableArrayList<Question>()

    val dogeController : DogeController by inject()

    fun load(){
        runAsync {
            dogeController.getQuestions().observable()
        }ui {
            convertToViewModel(it)
            dataQuestions.addAll(it)
        }
    }

    private fun convertToViewModel(it: ObservableList<Question>) {
        questions.addAll(it.map(::QuestionViewModel))
    }
}