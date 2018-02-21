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


    // duplicate question declarations with different types
    // duplicate labels (warning)




    //duplicate labels (warning)
    fun checkForDuplicateQuestions(): List<String> {
        val distinctQuestions = table.values.distinctBy { question -> question.label }

        val duplicateQuestions = table.values - distinctQuestions

        val duplicateQuestionText =  duplicateQuestions.map { x -> x.label }

        return duplicateQuestionText
    }

    fun checkForDuplicateQuestionsWithDifferentTypes(): List<Any> {
        val questions = table.values.map { it.label to it.value.type }

        val distinctQuestions = questions.toMap()

        val duplicateQuestion = questions - distinctQuestions

        return duplicateQuestion
    }
}