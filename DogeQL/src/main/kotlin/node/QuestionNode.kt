package node

import data.question.Question

data class QuestionNode(val question: Question) : Node(){
    override fun validate(): Boolean  = true
}