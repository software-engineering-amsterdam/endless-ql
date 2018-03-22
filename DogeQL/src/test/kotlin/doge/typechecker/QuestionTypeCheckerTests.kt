package doge.typechecker

import doge.data.question.Question
import doge.data.value.BooleanValue
import doge.typechecker.pass.DuplicatePass
import org.junit.Test

class  QuestionTypeCheckerTests {

    private val typeChecker = DuplicatePass()

    @Test
    fun `duplicate labels should be marked as duplicate`() {
        val input = hashMapOf(
                "question1" to Question("q1", "Question one?", BooleanValue(false)),
                "question2" to Question("q2", "Question one?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        val expectedResult = setOf("Question one?")

        result shouldEqual expectedResult
    }

    @Test
    fun `no duplicate labels should not be marked as duplicate`() {
        val input = hashMapOf(
                "question1" to Question("q1", "Question one?", BooleanValue(false)),
                "question2" to Question("q2", "Question two?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        result shouldEqual emptySet()
    }

}