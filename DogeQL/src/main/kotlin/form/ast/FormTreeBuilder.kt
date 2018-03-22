package form.ast

import form.common.Name
import form.data.question.Question
import form.data.symbol.SymbolTable
import form.expression.SourceLocation
import form.node.ExpressionNode
import form.node.Node
import form.node.QuestionNode
import form.node.RootNode
import java.util.*

class FormTreeBuilder(var symbolTable: SymbolTable) {

    private val nodeStack = ArrayDeque<Node>()

    init {
        nodeStack.push(RootNode(symbolTable))
    }

    fun pushQuestion(question: Question) {
        nodeStack.first.addChild(QuestionNode(symbolTable, question))
    }

    fun pushExpression(reference: Name, sourceLocation: SourceLocation) {
        nodeStack.push(ExpressionNode(symbolTable, reference, sourceLocation))
    }

    fun build(): Node {
        val node = nodeStack.pop()

        if (!nodeStack.isEmpty()) {
            nodeStack.first.addChild(node)
        }

        return node
    }
}