package ui

import data.question.Question
import data.value.IntegerValue
import tornadofx.*
import ui.question.FormViewModel
import ui.question.QuestionField
import ui.question.QuestionViewModel


class DogeMainView: View() {

    private val formQuestions : FormViewModel by inject()


    override val root = vbox()

    init {
        with(root){
            form{
                fieldset {
                    children.bind(formQuestions.questions){

                        field(it.label.value) {
                            add(QuestionField(it))
                        }

                    }
                }
            }

            button("Save") {
                setOnAction { save() }
            }
        }
        runAsync {
            formQuestions.load()
        }
    }


    fun save(){
        formQuestions.questions.forEach{
            x ->
            println("Value: ${x.value}")
            println("Value: ${x.label.value}")
        }

        formQuestions.questions.add(QuestionViewModel( Question("sds", IntegerValue(1))))
    }
}
