package lexer

import org.junit.Assert
import org.junit.Test

class DateLiteralTests {

    @Test
    fun when_ValidDate_Expect_Success() {
        val input = "01-02-1970"
        val result = ParserHost(input).parser.literal().LIT_DATE()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_ValidDateReverse_Expect_Success() {
        val input = "1970-02-01"
        val result = ParserHost(input).parser.literal().LIT_DATE()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_MissingDay_Expect_Failure() {
        val input = "02-1970"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

    @Test
    fun when_MissingMonth_Expect_Failure() {
        val input = "01-1970"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

    @Test
    fun when_MissingYear_Expect_Failure() {
        val input = "01-02"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DATE()
        Assert.assertNull(result)
    }

}
