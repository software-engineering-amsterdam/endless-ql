package ui

import data.Question
import javafx.scene.control.Label
import javafx.scene.control.ListView
import tornadofx.*
import ui.question.QuestionItemViewModel


class DogeMainView: View() {

    val questionItemViewModel : QuestionItemViewModel by inject()


    override val root = vbox {

        listview<Question> {
            itemsProperty().bind(questionItemViewModel.questions.observable())
            bindSelected(questionItemViewModel)
        }
    }

}