package ui.view.fragment

import tornadofx.Fragment
import tornadofx.vbox
import ui.controller.DogeController

class StylizedFormFragment : Fragment() {

    val model: DogeController by inject()

    override val root = vbox()

    init {
        with(root) {
            val style = model.style
            style?.let {
                val result = it.accept(FormFactory())
                add(result)
            }
        }
    }

}
