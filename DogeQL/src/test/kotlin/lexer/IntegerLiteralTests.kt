package lexer

import org.junit.Assert
import org.junit.Test

class IntegerLiteralTests {

    @Test
    fun when_0Int_Expect_Success() {
        val input = "0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_Multiple0Int_Expect_Success() {
        val input = "00000"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_Multiple0PadInt_Expect_Success() {
        val input = "000001"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_VeryLargeInt_Expect_Success() {
        val input = "987654321987654321"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NegativeInt_Expect_Success() {
        val input = "-1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NegativeZeroInt_Expect_Success() {
        val input = "-0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_VeryLargeNegativeInt_Expect_Success() {
        val input = "-98765432123456789"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_MultipleNegativeSignsInt_Expect_Success() {
        val input = "-----1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_HexNumber_Expect_Success() {
        val input = "0x1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NegativeHexNumber_Expect_Success() {
        val input = "-0x1"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_ZeroHexNumber_Expect_Success() {
        val input = "0x0"
        val result = ParserHost(input).parser.literal().LIT_INTEGER()
        ParseAssert.assertNotNullAndEqual(input, result)
    }

    @Test
    fun when_NoDigits_Expect_Failure() {
        val input = "fail"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_INTEGER()
        Assert.assertNull(result)
    }

    @Test
    fun when_DecimalNumber_Expect_Failure() {
        val input = "0.1"
        val result = ParserHost(input, expectError = true).parser.literal().LIT_INTEGER()
        Assert.assertNull(result)
    }

}
