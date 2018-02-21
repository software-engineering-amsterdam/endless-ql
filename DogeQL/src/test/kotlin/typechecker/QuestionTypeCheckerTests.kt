package typechecker

import data.BooleanValue
import data.Question
import org.amshove.kluent.shouldEqual
import org.junit.Test

class  QuestionTypeCheckerTests {

    private val typeChecker = QuestionTypeChecker()

    @Test
    fun when_DuplicateLabels_Expect_DuplicateLabel() {
        val input = hashMapOf(
                "question1" to Question("Question one?", BooleanValue(false)),
                "question2" to Question("Question one?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        val expectedResult = listOf("Question one?")

        result shouldEqual expectedResult
    }

    @Test
    fun when_NoDuplicateLabels_Expect_Empty() {
        val input = hashMapOf(
                "question1" to Question("Question one?", BooleanValue(false)),
                "question2" to Question("Question two?", BooleanValue(false))
        )

        val result = typeChecker.findDuplicateLabels(input)

        result shouldEqual emptyList()
    }

}