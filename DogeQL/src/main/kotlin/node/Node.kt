package node

import data.question.Question

abstract class Node {
    internal val children = ArrayList<Node>()

    fun addChild(child: Node) {
        children.add(child)
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

    open fun updateQuestion(question: Question) : Boolean = children.any {
        child -> child.updateQuestion(question)
    }

    abstract fun getEnabledQuestions() : ArrayList<Question>

    abstract fun validate(): Boolean

}