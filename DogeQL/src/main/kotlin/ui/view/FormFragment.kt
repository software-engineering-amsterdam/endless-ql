package ui.view

import javafx.geometry.Side
import tornadofx.*
import ui.controller.DogeController
import ui.model.ViewModelFactory

class FormFragment : Fragment(){

    private val defaultPageName = "DogeQL"

    val model: DogeController by inject()

    override val root = drawer(Side.LEFT, false, false) {
        item(defaultPageName, expanded = true)
        {
            scrollpane {
                form {
                    fieldset {
                        children.bind(model.questions) {
                            field(it.label) {
                                var viewModel = ViewModelFactory().createUiQuestionModel(it)
                                add(ViewFactory().createQuestionField(viewModel))
                            }
                        }
                    }
                }

            }
        }
    }


}