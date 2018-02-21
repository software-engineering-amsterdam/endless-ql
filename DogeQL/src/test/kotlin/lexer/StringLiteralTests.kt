package lexer

import org.junit.Assert
import org.junit.Test

class StringLiteralTests {

    @Test
    fun when_ValidString_Expect_Success() {
        val input = "\"Hello\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_SpacesInString_Expect_Success() {
        val input = "\"Hello World\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_EmptyString_Expect_Success() {
        val input = "\"\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_OnlySpacesInString_Expect_Success() {
        val input = "\"                    \""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NumberInString_Expect_Success() {
        val input = "\"1\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_MissingQuoteInString_Expect_Failure() {
        val input = "\""
        val result = ParserHost(input).parser.literal().LIT_STRING()
        Assert.assertNull(result)
    }

    @Test
    fun when_UnquotedString_Expect_Failure() {
        val input = "Hello"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_STRING()
        Assert.assertNull(result)
    }

}
