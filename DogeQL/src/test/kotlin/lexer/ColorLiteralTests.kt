package lexer

import org.junit.Assert
import org.junit.Test

class ColorLiteralTests {

    @Test
    fun when_ValidColor_Expect_Success() {
        val input = "#123ABC"
        val result = ParserHost(input).parser.literal().LIT_COLOR()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NoLeadingSign_Expect_Failure() {
        val input = "123ABC"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_COLOR()
        Assert.assertNull(result)
    }

    @Test
    fun when_NoHexadecimals_Expect_Failure() {
        val input = "#"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_COLOR()
        Assert.assertNull(result)
    }

}
