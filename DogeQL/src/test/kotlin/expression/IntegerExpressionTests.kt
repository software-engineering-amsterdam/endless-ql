package expression

import data.IntegerValue
import org.junit.Assert
import org.junit.Test
import javax.naming.OperationNotSupportedException

class IntegerExpressionTests {

    @Test
    fun when_ValidAddition_Expect_Success() {
        val result = IntegerValue(10) + IntegerValue(5)
        Assert.assertEquals(15, result.integerValue.value)
    }

    @Test
    fun when_ValidSubtraction_Expect_Success() {
        val result = IntegerValue(10) - IntegerValue(5)
        Assert.assertEquals(5, result.integerValue.value)
    }

    @Test
    fun when_ValidMultiplication_Expect_Success() {
        val result = IntegerValue(10) * IntegerValue(5)
        Assert.assertEquals(50, result.integerValue.value)
    }

    @Test
    fun when_ValidDivision_Expect_Success() {
        val result = IntegerValue(10) / IntegerValue(5)
        Assert.assertEquals(2, result.integerValue.value)
    }

    @Test
    fun when_ValidEquality_Expect_Success() {
        val result = IntegerValue(10) == IntegerValue(10)
        Assert.assertTrue(result)
    }

    @Test
    fun when_ValidInequality_Expect_Success() {
        val result = IntegerValue(10) != IntegerValue(5)
        Assert.assertTrue(result)
    }

    @Test
    fun when_EqualityInverse_Expect_Success() {
        val resultTrue = IntegerValue(10) == IntegerValue(10)
        val resultFalse = IntegerValue(10) != IntegerValue(10)
        Assert.assertTrue(resultTrue)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun when_ValidGreaterThan_Expect_Success() {
        val result = IntegerValue(10) > IntegerValue(5)
        Assert.assertTrue(result)
    }

    @Test
    fun when_ValidLessThan_Expect_Success() {
        val result = IntegerValue(5) < IntegerValue(10)
        Assert.assertTrue(result)
    }

    @Test
    fun when_ValidLessThanGreaterThan_Expect_Success() {
        val resultTrue = IntegerValue(10) > IntegerValue(5)
        Assert.assertTrue(resultTrue)

        val resultFalse = IntegerValue(10) < IntegerValue(5)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun when_ValidGreaterThanOrEqual_Expect_Success() {
        val result1 = IntegerValue(10) >= IntegerValue(5)
        Assert.assertTrue(result1)

        val result2 = IntegerValue(10) >= IntegerValue(10)
        Assert.assertTrue(result2)
    }

    @Test
    fun when_ValidLessThanOrEqual_Expect_Success() {
        val result1 = IntegerValue(5) <= IntegerValue(10)
        Assert.assertTrue(result1)

        val result2 = IntegerValue(10) <= IntegerValue(10)
        Assert.assertTrue(result2)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun when_Negate_Expect_Exception() {
        !IntegerValue(1)
    }

}
