package lexer

import org.junit.Assert
import org.junit.Test

class DecimalLiteralTests {

    @Test
    fun when_0Decimal_Expect_Success() {
        val input = "0.0"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_Leading0Decimal_Expect_Success() {
        val input = "00000.0"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_Trailing0Decimal_Expect_Success() {
        val input = "0.00000"
        val result = ParserHost(input).parser.literal().LIT_DECIMAL()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NoDotDecimal_Expect_Failure() {
        val input = "0"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_DECIMAL()
        Assert.assertNull(result)
    }

}
