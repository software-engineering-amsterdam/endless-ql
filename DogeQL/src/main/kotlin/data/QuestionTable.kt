package data

import common.Identifier

class QuestionTable {

    private val table = HashMap<Identifier, Question>()

    fun register(identifier: Identifier, question: Question) {
        table[identifier] = question
    }

    fun print(){
        table.forEach { identifier, question ->
            println("ID: $identifier, Question: $question ")
        }
    }
}