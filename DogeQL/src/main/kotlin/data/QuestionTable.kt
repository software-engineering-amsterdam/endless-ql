package data

import common.Name
import java.util.*

class QuestionTable {

    private val table = HashMap<Name, Question>()

    fun register(name: Name, question: Question) {
        table[name] = question
    }

    fun print(){
        table.forEach { identifier, question ->
            println("ID: $identifier, Question: $question ")
        }
    }
}