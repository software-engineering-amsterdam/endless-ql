package data

import common.Identifier
import java.util.*

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

    //duplicate labels (warning)
    fun checkForDuplicateQuestions(): List<String> {
        val distinctQuestions = table.values.distinctBy { question -> question.questionText }

        val duplicateQuestions = table.values - distinctQuestions

        val duplicateQuestionText =  duplicateQuestions.map { x -> x.questionText }

        return duplicateQuestionText
    }

    fun checkForDuplicateQuestionsWithDifferentTypes(): List<Any> {
        val questions = table.values.map { it.questionText to it.value.type }

        val distinctQuestions = questions.toMap()

        val duplicateQuestion = questions - distinctQuestions

        return duplicateQuestion
    }
}