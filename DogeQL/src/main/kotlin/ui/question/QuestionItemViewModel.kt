package ui.question

import data.Question
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.control.ListView
import tornadofx.ItemViewModel
import tornadofx.observable
import tornadofx.singleAssign
import ui.DogeController
import java.util.*


class QuestionItemViewModel: ItemViewModel<Question>() {

    val dogeController: DogeController by inject<DogeController>()
    var questions: ListView<Question> by singleAssign()

    val label = bind(Question::label)
    val value = bind(Question::value)

    fun refresh(){
        runAsync {
            updateMessage("loading")
            dogeController.getQuestions().observable()
        } ui {
            questions.items = it
        }

    }

}