package ui.view

import data.question.Question
import data.value.IntegerValue
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.control.ScrollPane
import javafx.scene.layout.FlowPane
import tornadofx.*
import ui.model.QuestionFormModel
import ui.model.QuestionModel


class DogeMainView : View() {

    private val model: QuestionFormModel by inject()

    val t = SimpleIntegerProperty()

    override val root = scrollpane()

    init {
        root.minHeight = 400.0
        root.minWidth = 400.0
        with(root) {

            form {
                fieldset {
                    children.bind(model.questions) {
                        field(it.item.label) {
                            add(QuestionField(it))
                        }
                    }
                }
                button("Save") {
                    action {
                        save()
                    }
                }
            }

            runAsync {
                model.load()
            }
        }
    }

    private fun save() {
        model.commit()
        model.load()
        print("sdfsdf")    }
}
