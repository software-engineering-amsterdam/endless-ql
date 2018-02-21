package expression

import data.BooleanValue
import org.junit.Assert
import org.junit.Test
import javax.naming.OperationNotSupportedException

class BooleanExpressionTests {

    @Test(expected = OperationNotSupportedException::class)
    fun when_Addition_Expect_Exception() {
        BooleanValue(true) + BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun when_Subtraction_Expect_Exception() {
        BooleanValue(true) - BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun when_Multiplication_Expect_Exception() {
        BooleanValue(true) * BooleanValue(true)
    }

    @Test(expected = OperationNotSupportedException::class)
    fun when_Division_Expect_Exception() {
        BooleanValue(true) / BooleanValue(true)
    }

    @Test
    fun when_Equality_Expect_Success() {
        val result1 = BooleanValue(true) == BooleanValue(true)
        Assert.assertTrue(result1)

        val result2 = BooleanValue(false) == BooleanValue(false)
        Assert.assertTrue(result2)
    }

    @Test
    fun when_ValidInequality_Expect_Success() {
        val result = BooleanValue(false) != BooleanValue(true)
        Assert.assertTrue(result)
    }

    @Test
    fun when_EqualityInverse_Expect_Success() {
        val resultTrue = BooleanValue(true) == BooleanValue(true)
        val resultFalse = BooleanValue(true) != BooleanValue(true)
        Assert.assertTrue(resultTrue)
        Assert.assertFalse(resultFalse)
    }

    @Test
    fun when_GreaterThan_Expect_Second() {
        val result = BooleanValue(true) > BooleanValue(true)
        Assert.assertFalse(result)
    }

    @Test
    fun when_LessThan_Expect_Second() {
        val result = BooleanValue(true) < BooleanValue(true)
        Assert.assertFalse(result)
    }

    @Test
    fun when_GreaterThanOrEqual_Expect_First() {
        val result = BooleanValue(true) >= BooleanValue(true)
        Assert.assertTrue(result)
    }

    @Test
    fun when_LessThanOrEqual_Expect_First() {
        val result = BooleanValue(true) <= BooleanValue(true)
        Assert.assertTrue(result)
    }

}