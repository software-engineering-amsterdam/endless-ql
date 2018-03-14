package node

import data.question.Question

data class QuestionNode(val question: Question) : Node(){
    override fun getEnabledQuestions(): ArrayList<Question> = arrayListOf(question)

    override fun getQuestions() : ArrayList<Question> = arrayListOf(question)

    override fun validate(): Boolean  = true
}