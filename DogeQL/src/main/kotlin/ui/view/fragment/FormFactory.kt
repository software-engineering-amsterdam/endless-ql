package ui.view.fragment

import javafx.geometry.Side
import javafx.scene.Node
import javafx.scene.control.TextField
import qls.ast.model.*
import qls.visitor.QlsVisitor
import tornadofx.*

class FormFactory : View(), QlsVisitor<Node> {

    private var padding = 0.0

    override val root = Drawer(Side.LEFT, false, false)

    override fun visit(styleSheet: StyleSheet): Node {
        styleSheet.pages.forEach {
            it.accept(this)
        }
        return root
    }

    override fun visit(page: Page): Node {

        with(root) {
            item(page.title) {
                scrollpane {
                    form {
                        page.styles.forEach {
                            add(it.accept(this@FormFactory))
                        }
                    }
                }
            }
        }

        return root
    }

    override fun visit(style: Style): Node {
        return style.accept(this)
    }

    override fun visit(section: Section): Node {
        val fieldSet = Fieldset(section.title)

        fieldSet.paddingLeft = padding

        padding += 10

        section.elements.forEach {
            fieldSet.add(it.accept(this))
        }

        padding -= 10

        return fieldSet
    }

    override fun visit(defaultAttributes: DefaultAttributes): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(question: Question): Node {
        val field = Field(question.name)

        field.add(
                TextField()
        )

        return field
    }

    override fun visit(element: Element): Node {
        return element.accept(this)
    }
}