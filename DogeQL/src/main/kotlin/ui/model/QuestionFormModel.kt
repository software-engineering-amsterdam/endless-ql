package ui.model

import data.question.Question
import data.value.IntegerValue
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
            updateViewModel(it)
            dataQuestions = it
        }
    }


    private fun updateViewModel(newDataQuestions: List<Question>) {
//        var toAdd = newDataQuestions - dataQuestions

        val toAdd = newDataQuestions.filter {
            q -> q !in dataQuestions || q.readOnly
        }

        questions.removeIf { q ->
            q.item !in newDataQuestions || q.item.readOnly
        }

        // Only add new questions, leave old questions as is
        toAdd.forEach { q ->
            questions.add(newDataQuestions.indexOf(q), QuestionModel(q))
        }

    }
}