package ui.model

import data.question.Question
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ItemViewModel
import ui.controller.DogeController

class QuestionFormModel : ItemViewModel<QuestionModel>() {

    var questions: ObservableList<QuestionModel> = FXCollections.observableArrayList<QuestionModel>()

    private var dataQuestions = listOf<Question>()

    private val dogeController: DogeController by inject()

    override fun onCommit() {
        questions.forEach { x ->
            x.commit()
        }
    }

    fun load() {
        runAsync {
            dogeController.getQuestions()
        } ui {
            convertToViewModel(it)
            dataQuestions = it
        }
    }

    private fun convertToViewModel(newDataQuestions: List<Question>) {
        val toAdd = newDataQuestions - dataQuestions

        questions.removeIf { q ->
            q.item !in newDataQuestions
        }

        toAdd.forEach { q ->
            questions.add(newDataQuestions.indexOf(q), QuestionModel(q))
        }
    }
}