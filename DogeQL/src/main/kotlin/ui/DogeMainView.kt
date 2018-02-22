package ui

import data.Question
import javafx.scene.control.Label
import javafx.scene.control.ListView
import tornadofx.*


class DogeMainView: View() {

    var dogeController: DogeController by singleAssign()
    var questions: ListView<Question> by singleAssign()
    var label: Label by singleAssign()

    override val root = vbox {

        dogeController = DogeController()

        questions = listview {
            cellFormat {
                graphic = cache {
                    form {
                        fieldset {
                            field("Question") {
                                label(it.label)
                            }
                        }
                    }
                }
            }
        }

        label = label("Hey")

        runAsync {
            dogeController.getQuestions().observable()
        } ui {
            questions.items = it
        }

    }

}