package node

import data.question.Question
import data.value.BaseSymbolValue

abstract class Node() {

    protected var parent: Node? = null

    internal val children = ArrayList<Node>()

    fun addChild(child: Node) {
        child.addParent(this)
        children.add(child)
    }

    fun addParent(parent: Node) {
        this.parent = parent
    }

    fun getChildren(): ArrayList<Node> {
        return children
    }

    fun hasChildren(): Boolean {
        return children.isEmpty()
    }

    open fun getQuestions(): ArrayList<Question> {
        val questions = children.flatMap { child ->
            child.getQuestions()
        }

        return ArrayList(questions)
    }

    fun findQuestion(name: String): Question {
        val match = children.find {
            it is QuestionNode && it.question.name == name
        }

        if (match == null) {
            throw NoSuchElementException()
        } else {
            return (match as QuestionNode).question
        }
    }

    fun findValueForReference(reference: String): BaseSymbolValue? {

        parent?.let {
            val match = it.children.find {
                it is QuestionNode && it.question.name == reference
            }

            if (match is QuestionNode) {
                return match.question.value
            } else {
                return it.findValueForReference(reference)
            }
        }

        return null
    }

    abstract fun getEnabledQuestions(): ArrayList<Question>

    abstract fun validate(): Boolean
}