package ui.view

import javafx.collections.ObservableList
import javafx.geometry.Side
import qls.model.StyleSheet
import tornadofx.*
import ui.model.QuestionModel

class UiRenderer : View() {

    override val root = Drawer(Side.LEFT, false, false)

    fun render(questions: ObservableList<QuestionModel>, styleSheet: StyleSheet): Drawer {
        val fieldFactory = ViewFactory()


        with(root) {
            styleSheet.pages.forEach { page ->
                item(page.title) {
                    scrollpane {
                        form {
                            page.sections.forEach { section ->
                                fieldset(section.title) {
                                    children.bind(questions) {
                                        field(it.item.label) {
                                            add(ViewFactory().createQuestionField(it))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        root.items.first().expanded = true
        return root
    }

    fun render(questions: ObservableList<QuestionModel>): Drawer {
        with(root) {
            item("Page 1", expanded = true)
            {
                scrollpane {
                    form {
                        fieldset {
                            children.bind(questions) {
                                field(it.item.label) {
                                    add(ViewFactory().createQuestionField(it))
                                }
                            }
                        }
                    }

                }
            }


        }
        return root
    }
}