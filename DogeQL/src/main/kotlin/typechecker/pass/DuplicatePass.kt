package typechecker.pass

import common.Name
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import typechecker.NodePass
import typechecker.TypeCheckResult

class DuplicatePass(result: TypeCheckResult) : NodePass<Unit>(result) {

    private val visitedLabels = hashSetOf<Name>()
    private val visitedNames = hashSetOf<Name>()

    override fun visit(node: Node) {
        node.children.forEach { child -> child.accept(this) }
    }

    override fun visit(rootNode: RootNode) {
        rootNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(questionNode: QuestionNode) {
        val label = questionNode.question.label
        val name = questionNode.question.name

        if (!visitedLabels.add(label)) {
            result.duplicateLabels.add(label)
        }

        if (!visitedNames.add(name)) {
            result.duplicateNames.add(name)
        }

        questionNode.children.forEach { child -> child.accept(this) }
    }

    override fun visit(expressionNode: ExpressionNode) {
        expressionNode.children.forEach { child -> child.accept(this) }
    }

}