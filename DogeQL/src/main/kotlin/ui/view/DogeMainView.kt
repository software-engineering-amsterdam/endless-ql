package ui.view

import tornadofx.*
import ui.model.QuestionFormModel


class DogeMainView: View() {

    private val model: QuestionFormModel by inject()


    override val root = vbox()

    init {
        with(root){
            form{
                fieldset {
                    children.bind(model.questions){
                        field(it.item.label) {
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
            model.load()
        }
    }


    fun save(){
        model.questions.forEach{ x ->
            x.commit()
            println("label: ${x.item.label}")
            println("Integer Value: ${x.integerValue}")
            println("String Value: ${x.stringValue}")
            println("BigDeciamal Value: ${x.decimalValue}")
            println("Money Value: ${x.moneyValue}")
            println("Boolean Value: ${x.booleanValue}")
        }
    }
}
