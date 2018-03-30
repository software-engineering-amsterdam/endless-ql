package ui.view.fragment

import javafx.geometry.Side
import tornadofx.*
import ui.controller.DogeController
import ui.model.ViewModelFactory
import ui.view.QuestionFieldFactory

class FormFragment : Fragment() {

    private val defaultPageName = "DogeQL"

    val model: DogeController by inject()

    override var root = drawer(Side.LEFT, false, false) {
        item(defaultPageName, expanded = true)
        {
            scrollpane {
                form {
                    fieldset {
                        children.bind(model.questions) {
                            field(it.label) {
                                val viewModel = ViewModelFactory().createUiQuestionModel(it)
                                add(QuestionFieldFactory().createQuestionField(viewModel))
                            }
                        }
                    }
                }

            }
        }
    }
}