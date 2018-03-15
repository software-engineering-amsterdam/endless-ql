package typechecker

import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode

abstract class NodePass<out O>(val result: TypeCheckResult) {

    abstract fun visit(node: Node): O

    abstract fun visit(rootNode: RootNode): O

    abstract fun visit(questionNode: QuestionNode): O

    abstract fun visit(expressionNode: ExpressionNode): O

}