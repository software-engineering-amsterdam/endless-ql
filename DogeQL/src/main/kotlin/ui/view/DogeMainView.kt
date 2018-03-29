package ui.view

import javafx.scene.layout.VBox
import qls.model.Page
import qls.model.Section
import qls.model.StyleSheet
import ui.model.QuestionFormModel
import tornadofx.*


class DogeMainView : View() {

    private val model: QuestionFormModel by inject()

    override val root = VBox()

    private val minHeight = 400.0
    private val minWidth = 400.0

    init {
        root.minHeight = minHeight
        root.minWidth = minWidth

        with(root) {
            val view = UiRenderer().render(model.questions)
            add(view)


//            form {
//                fieldset {
//                    children.bind(model.questions) {
//                        field(it.item.label) {
//                            add(ViewFactory().createQuestionField(it))
//                        }
//                    }
//                }
//                button("Save") {
//                    action {
//                        save()
//                    }
//                }
//            }

            runAsync {
                model.load()
            }
        }
    }

    private fun save() {
        model.commit()
        model.load()
    }


    private fun temp(): StyleSheet {
        return StyleSheet(
                listOf(
                        Page(
                                listOf(
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 1"),
                                        Section("section 2")
                                ),
                                "Page 1"
                        ),
                        Page(
                                listOf(
                                        Section("section 3")
                                ),
                                "Page 2"
                        )
                )
        )
    }
}


