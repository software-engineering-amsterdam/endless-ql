package node

import data.question.Question

class RootNode : Node() {
    override fun getEnabledQuestions(): ArrayList<Question> {
        val questions = children.flatMap { child ->
            child.getEnabledQuestions()
        }

        return ArrayList(questions)
    }

    override fun validate(): Boolean = children.all { child ->
        child.validate()
    }
}