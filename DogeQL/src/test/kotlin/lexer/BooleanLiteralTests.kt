package lexer

import org.junit.Assert
import org.junit.Test

class BooleanLiteralTests {

    @Test
    fun when_TrueBoolean_Expect_Success() {
        val input = "true"
        val result = ParserHost("true").parser.literal().LIT_BOOLEAN()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_FalseBoolean_Expect_Success() {
        val input = "false"
        val result = ParserHost("false").parser.literal().LIT_BOOLEAN()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_InvalidBoolean_Expect_Failure() {
        val input = "fail"
        val result = ParserHost("fail", expectError = true).parser.literal().LIT_BOOLEAN()
        Assert.assertNull(result)
    }

}
