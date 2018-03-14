package ast

import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
import data.question.Question
import expression.Expression
import java.util.*

class FormTreeBuilder {

    private val nodeStack = ArrayDeque<Node>()

    init {
        nodeStack.push(RootNode())
    }

    fun pushQuestion(question: Question) {
        nodeStack.first.addChild(QuestionNode(question))
    }

    fun pushExpression(expression: Expression) {
        nodeStack.push(ExpressionNode(expression))
    }

    fun build(): Node {
        val node = nodeStack.pop()

        if (!nodeStack.isEmpty()) {
            nodeStack.first.addChild(node)
        }

        return node
    }
}