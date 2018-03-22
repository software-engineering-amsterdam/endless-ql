package ql.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.logic.type.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public final class QLDataTypeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testIncompatibleTypesCasting() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
        QLDataTypeString val2 = new QLDataTypeString("2");

        exception.expect(java.lang.ClassCastException.class);

        val1.add(val2);
    }

    @Test
    public void testIntegerNegation() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
        QLDataTypeInteger result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(result.getValue().compareTo(BigInteger.valueOf(-10)), 0);
    }

    @Test
    public void testIntegerAddition() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(7));
        QLDataTypeNumeric result = val1.add(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(17)), 0);

    }

    @Test
    public void testIntegerSubtraction() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(17));
        QLDataTypeNumeric result = val1.subtract(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(-7)), 0);
    }

    @Test
    public void testIntegerMultiplication() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(3));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(7));
        QLDataTypeNumeric result = val1.multiply(val2);
        BigInteger resultValue = (BigInteger) result.getValue();
        Assert.assertEquals(resultValue.compareTo(BigInteger.valueOf(21)), 0);
    }

    @Test
    public void testIntegerDivision() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(9));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(3));
        QLDataType result = val1.divide(val2);

        Assert.assertEquals(((BigInteger) result.getValue()).compareTo(BigInteger.valueOf(3)), 0);
    }

    @Test
    public void testDecimalAddition() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("3.2"));
        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("2.1"));

        QLDataTypeDecimal result = val1.add(val2);

        Assert.assertEquals(result.getValue().setScale(8, RoundingMode.HALF_UP).compareTo(new BigDecimal(5.3).setScale(8, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testDecimalSubtraction() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("43.16"));
        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("3.15"));

        QLDataTypeDecimal result = val1.subtract(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(40.01).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testDecimalMultiplication() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("3.2"));
        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("2.1"));

        QLDataTypeDecimal result = val1.multiply(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(6.72).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testDecimalDivision() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("6.72"));
        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("2.1"));

        QLDataTypeDecimal result = val1.divide(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(3.2).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("3.2"));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(2));

        QLDataTypeDecimal result = val1.add(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(5.2).setScale(4, RoundingMode.HALF_UP)), 0);

    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("43.16"));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(3));

        QLDataTypeDecimal result = val1.subtract(val2);

        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(40.16).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("3.2"));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(2));

        QLDataTypeDecimal result = val1.multiply(val2);
        Assert.assertEquals(result.getValue().setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(6.4).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("6.72"));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(2));

        QLDataTypeNumeric result = val1.divide(val2);
        BigDecimal resultValue = (BigDecimal) result.getValue();

        Assert.assertEquals(resultValue.setScale(4, RoundingMode.HALF_UP).compareTo(new BigDecimal(3.36).setScale(4, RoundingMode.HALF_UP)), 0);
    }

    @Test
    public void testLogicalNegation() {
        QLDataTypeBoolean qlTrue = new QLDataTypeBoolean(true);
        QLDataTypeBoolean qlFalse = new QLDataTypeBoolean(false);

        QLDataTypeBoolean result = qlTrue.negate();
        Assert.assertFalse(result.getValue());
    }

    @Test
    public void testLogicalAnd() {
        QLDataTypeBoolean qlTrue = new QLDataTypeBoolean(true);
        QLDataTypeBoolean qlFalse = new QLDataTypeBoolean(false);

        // true && false -> false
        QLDataTypeBoolean result = qlTrue.and(qlFalse);
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
        QLDataTypeBoolean qlTrue = new QLDataTypeBoolean(true);
        QLDataTypeBoolean qlFalse = new QLDataTypeBoolean(false);

        Assert.assertEquals(qlTrue.getType(), qlFalse.getType());

        // true || false -> true
        QLDataTypeBoolean result = qlTrue.or(qlFalse);
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
        QLDataTypeString val1 = new QLDataTypeString("One");
        QLDataTypeString val2 = new QLDataTypeString("Two");
        QLDataTypeString result = val1.add(val2);
        Assert.assertEquals("OneTwo", result.getValue());
    }

    @Test
    public void testIntegerComparision() {
        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(7));

        QLDataTypeBoolean result = val1.equals(val2);
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
        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("10.4"));
        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("7.4"));

        QLDataTypeBoolean result = val1.equals(val2);
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
