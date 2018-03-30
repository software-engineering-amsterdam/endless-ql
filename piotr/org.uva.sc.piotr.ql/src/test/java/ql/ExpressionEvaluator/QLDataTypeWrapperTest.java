package ql.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.logic.type.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class QLDataTypeWrapperTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testIncompatibleTypesCasting() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(10));
        QLString val2 = new QLString("2");

        exception.expect(RuntimeException.class);

        QLDataTypeWrapper sth = val1.add(val2);
    }

    @Test
    public void testIntegerNegation() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(10));
        QLInteger result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(result.getValue().compareTo(BigInteger.valueOf(-10)), 0);
    }

    @Test
    public void testIntegerAddition() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(10));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(7));
        QLDataTypeWrapper result = val1.add(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(17)), 0);

    }

    @Test
    public void testIntegerSubtraction() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(10));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(17));
        QLDataTypeWrapper result = val1.subtract(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(-7)), 0);
    }

    @Test
    public void testIntegerMultiplication() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(3));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(7));
        QLDataTypeWrapper result = val1.multiply(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(21)), 0);
    }

    @Test
    public void testIntegerDivision() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(9));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(3));
        QLDataTypeWrapper result = val1.divide(val2);

        Assert.assertEquals(((BigInteger) result.getValue()).compareTo(BigInteger.valueOf(3)), 0);
    }

    @Test
    public void testDecimalAddition() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("3.2"));
        QLDecimal val2 = new QLDecimal(new BigDecimal("2.1"));

        QLDecimal result = val1.add(val2);

        Assert.assertEquals(result.getValue().setScale(8, RoundingMode.HALF_UP).compareTo(new BigDecimal(5.3).setScale(8, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testDecimalSubtraction() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("43.16"));
        QLDecimal val2 = new QLDecimal(new BigDecimal("3.15"));

        QLDecimal result = val1.subtract(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(40.01).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testDecimalMultiplication() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("3.2"));
        QLDecimal val2 = new QLDecimal(new BigDecimal("2.1"));

        QLDecimal result = (QLDecimal) val1.multiply(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(6.72).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testDecimalDivision() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("6.72"));
        QLDecimal val2 = new QLDecimal(new BigDecimal("2.1"));

        QLDecimal result = val1.divide(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(3.2).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("3.2"));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(2));

        QLDecimal result = val1.add(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(5.2).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("43.16"));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(3));

        QLDecimal result = val1.subtract(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(40.16).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("3.2"));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(2));

        QLDecimal result = (QLDecimal) val1.multiply(val2);
        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(6.4).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("6.72"));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(2));

        QLDecimal result = val1.divide(val2);
        BigDecimal resultValue = result.getValue();

        Assert.assertEquals(resultValue.setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(3.36).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testLogicalNegation() {
        QLBoolean qlTrue = new QLBoolean(true);
        QLBoolean qlFalse = new QLBoolean(false);

        QLBoolean result = qlTrue.negate();
        Assert.assertFalse(result.getValue());
    }

    @Test
    public void testLogicalAnd() {
        QLBoolean qlTrue = new QLBoolean(true);
        QLBoolean qlFalse = new QLBoolean(false);

        // true && false -> false
        QLBoolean result = qlTrue.and(qlFalse);
        Assert.assertFalse(result.getValue());

        // false && true -> false
        result = qlFalse.and(qlTrue);
        Assert.assertFalse(result.getValue());

        // false && false -> false
        result = qlFalse.and(qlFalse);
        Assert.assertFalse(result.getValue());

        // true && true -> true
        result = qlTrue.and(qlTrue);
        Assert.assertTrue(result.getValue());

    }

    @Test
    public void testLogicalOr() {
        QLBoolean qlTrue = new QLBoolean(true);
        QLBoolean qlFalse = new QLBoolean(false);

        Assert.assertEquals(qlTrue.getType(), qlFalse.getType());

        // true || false -> true
        QLBoolean result = qlTrue.or(qlFalse);
        Assert.assertTrue(result.getValue());

        // false || true -> true
        result = qlFalse.or(qlTrue);
        Assert.assertTrue(result.getValue());

        // false || false -> false
        result = qlFalse.or(qlFalse);
        Assert.assertFalse(result.getValue());

        // true || true -> true
        result = qlTrue.or(qlTrue);
        Assert.assertTrue(result.getValue());

    }

    @Test
    public void testStringConcatenation() {
        QLString val1 = new QLString("One");
        QLString val2 = new QLString("Two");
        QLDataTypeWrapper result = val1.add(val2);
        Assert.assertEquals("OneTwo", result.getValue());
    }

    @Test
    public void testIntegerComparision() {
        QLInteger val1 = new QLInteger(BigInteger.valueOf(10));
        QLInteger val2 = new QLInteger(BigInteger.valueOf(7));

        QLBoolean result = val1.equals(val2);
        Assert.assertFalse(result.getValue());

        result = val1.notEquals(val2);
        Assert.assertTrue(result.getValue());

        result = val1.lessThan(val2);
        Assert.assertFalse(result.getValue());

        result = val1.lessEqual(val2);
        Assert.assertFalse(result.getValue());

        result = val1.greaterThan(val2);
        Assert.assertTrue(result.getValue());

        result = val1.greaterEqual(val2);
        Assert.assertTrue(result.getValue());
    }

    @Test
    public void testDecimalComparision() {
        QLDecimal val1 = new QLDecimal(new BigDecimal("10.4"));
        QLDecimal val2 = new QLDecimal(new BigDecimal("7.4"));

        QLBoolean result = val1.equals(val2);
        Assert.assertFalse(result.getValue());

        result = val1.notEquals(val2);
        Assert.assertTrue(result.getValue());

        result = val1.lessThan(val2);
        Assert.assertFalse(result.getValue());

        result = val1.lessEqual(val2);
        Assert.assertFalse(result.getValue());

        result = val1.greaterThan(val2);
        Assert.assertTrue(result.getValue());


        result = val1.greaterEqual(val2);
        Assert.assertTrue(result.getValue());

    }


}
