package ui

import data.question.Question
import tornadofx.*
import ui.question.QuestionField
import ui.question.QuestionViewModel


class DogeMainView: View() {

    private val questionViewModel : QuestionViewModel by inject()

    override val root = vbox()

    init {
        with(root){
            listview<Question> {
                itemsProperty().bind(questionViewModel.questions)
                bindSelected(questionViewModel)

                cellFormat {
                    graphic = cache {
                       form {
                            fieldset {
                                add(QuestionField(item))
                            }
                        }
                    }

                }
                runAsync {
                    questionViewModel.load()
                }
            }
            button("Save") {
                setOnAction { save() }
            }
        }
    }


    fun save(){
        var t = questionViewModel.questions;
    }
}
