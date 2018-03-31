package ui.view.fragment

import tornadofx.Drawer
import tornadofx.Fragment
import tornadofx.onChange
import tornadofx.vbox
import ui.controller.DogeController
import ui.visitor.StyleVisitor

class StylizedFormFragment : Fragment() {

    val model: DogeController by inject()

    override val root = vbox()



    init {
        with(root) {
            val style = model.style
            style?.let {
                val result = it.accept(StyleVisitor())
                children.add(0, result)
            }
        }



        model.questions.onChange {
            val style = model.style
            style?.let {
                val result = it.accept(StyleVisitor())

                val drawer = root.children.get(0) as Drawer

                val item = drawer.items.first { it.expanded }

                val index = drawer.items.indexOf(item)

                (result as Drawer).items[index] = item

                root.children.remove(0, 1)

                root.children.add(0, drawer)
            }
        }

    }
}
