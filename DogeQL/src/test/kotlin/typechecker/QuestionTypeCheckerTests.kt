package typechecker

import data.BooleanValue
import data.Question
import org.amshove.kluent.shouldEqual
import org.junit.Test

class  QuestionTypeCheckerTests {

    private val typeChecker = QuestionTypeChecker()

    @Test
    fun `duplicate labels should be marked as duplicate`() {
        val input = hashMapOf(
                "question1" to Question("Question one?", BooleanValue(false)),
                "question2" to Question("Question one?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        val expectedResult = setOf("Question one?")

        result shouldEqual expectedResult
    }

    @Test
    fun `no duplicate labels should not be marked as duplicate`() {
        val input = hashMapOf(
                "question1" to Question("Question one?", BooleanValue(false)),
                "question2" to Question("Question two?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        result shouldEqual emptySet()
    }

}