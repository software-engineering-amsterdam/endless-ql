package ui.view.fragment

import tornadofx.Fragment
import tornadofx.onChange
import tornadofx.vbox
import ui.controller.DogeController
import ui.visitor.StyleVisitor

class StylizedFormFragment : Fragment() {

    private val controller: DogeController by inject()

    override val root = vbox {
        val visitor = StyleVisitor()

        val style = controller.style
        style?.let {
            val result = it.accept(visitor)
            add(result)
        }


        // We can not bind this view to questions (there is nothing to bind)
        // That's why we manually change state
        controller.questions.onChange {
            controller.questions.forEach { question ->
                visitor.flatLayout[question.name]?.let {
                    it.isVisible = question.visible
                    it.isManaged = question.visible

                }
                visitor.questions[question.name]?.setViewModelValue(question)
            }
        }
    }
}
