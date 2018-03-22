package form.typechecker

import form.node.ExpressionNode
import form.node.Node
import form.node.QuestionNode
import form.node.RootNode

abstract class NodePass<out O>(val result: TypeCheckResult) {

    abstract fun visit(node: Node): O

    abstract fun visit(rootNode: RootNode): O

    abstract fun visit(questionNode: QuestionNode): O

    abstract fun visit(expressionNode: ExpressionNode): O

}