package ast

import common.Name
import data.question.Question
import data.symbol.SymbolTable
import expression.SourceLocation
import node.ExpressionNode
import node.Node
import node.QuestionNode
import node.RootNode
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