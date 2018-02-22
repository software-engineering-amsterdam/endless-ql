package ui

import data.Question
import tornadofx.*
import ui.question.ListViewItem
import javax.swing.text.html.ListView


class DogeMainView: View() {

    val dogeController : DogeController by inject()

    override val root = vbox()

    init {
        with(root){
            listview<Question> {
                cellFormat {
                    graphic = cache {
                       form {
                            fieldset {
                                field("hoi") {
                                    textfield()
                                }
                            }
                        }
                    }

                }
                runAsync {
                    updateMessage("loading")
                    dogeController.getQuestions().observable()
                } ui {
                    items = it
                }
            }
        }

    }
}