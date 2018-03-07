package ui.model

import data.question.Question
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import tornadofx.observable
import ui.controller.DogeController

class QuestionFormModel : ItemViewModel<QuestionModel>() {

    var questions: ObservableList<QuestionModel> = FXCollections.observableArrayList<QuestionModel>()

    private var dataQuestions = FXCollections.observableArrayList<Question>()

    private val dogeController: DogeController by inject()

    fun load() {
        runAsync {
            dogeController.getQuestions().observable()
        } ui {
            convertToViewModel(it)
            dataQuestions.addAll(it)
        }
    }

    private fun convertToViewModel(it: ObservableList<Question>) {
        questions.addAll(it.map(::QuestionModel))
    }
}