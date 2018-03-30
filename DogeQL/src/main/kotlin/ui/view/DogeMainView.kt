package ui.view

import tornadofx.View
import tornadofx.button
import tornadofx.vbox
import ui.controller.DogeController

class DogeMainView : View() {

    private val model: DogeController by inject()

    private val minHeight = 400.0
    private val minWidth = 400.0

    override val root = vbox {
        minHeight = this@DogeMainView.minHeight
        minWidth = this@DogeMainView.minWidth

        add(FormFragment::class)

        button("load") {
            setOnAction {
                model.load()
            }
        }
        button("test") {
            setOnAction {
                model.test()
            }
        }
    }
}


