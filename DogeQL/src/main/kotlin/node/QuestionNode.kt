package node

import data.question.Question

data class QuestionNode(val question: Question) : Node(){
    override fun getAllChildren() : ArrayList<Question> = arrayListOf(question)

    override fun validate(): Boolean  = true
}