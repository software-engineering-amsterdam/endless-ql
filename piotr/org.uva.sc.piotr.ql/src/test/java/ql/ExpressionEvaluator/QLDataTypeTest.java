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

//    @Test
//    public void testIntegerAddition() {
//        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
//        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(7));
//        QLDataTypeNumeric result = val1.add(val2);
//
//        Assert.assertEquals(result.getValue().compareTo(BigInteger.valueOf(17)), 0);
//
//    }
//
//    @Test
//    public void testIntegerSubtraction() {
//        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(10));
//        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(17));
//        QLDataTypeInteger result = val1.subtract(val2);
//
//        Assert.assertEquals(result.getValue().compareTo(BigInteger.valueOf(-7)), 0);
//    }
//
//    @Test
//    public void testIntegerMultiplication() {
//        QLDataTypeInteger val1 = new QLDataTypeInteger(BigInteger.valueOf(3));
//        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf(7));
//        QLDataTypeInteger result = val1.multiply(val2);
//
//        Assert.assertEquals(result.getValue().compareTo(BigInteger.valueOf(21)), 0);
//    }

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

//    @Test
//    public void testIntegerCastToDecimalDivision() {
//        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("6.72");
//        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf( "2");
//
//        QLDataType result = val1.divide(val2);
//        
//        Assert.assertEquals(result.getValue().setScale( 4, RoundingMode.HALF_UP).compareTo(new BigDecimal(3.36).setScale( 4, RoundingMode.HALF_UP)), 0);
//    }
//
//    @Test
//    public void testLogicalNegation() {
//        QLDataType QLDataTypeTrue = QLDataType.createValue(Expression.DataType.BOOLEAN, "true");
//
//        QLDataType result = QLDataTypeTrue.negate();
//        Assert.assertEquals(result.getType(), QLDataTypeTrue.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//    }
//
//    @Test
//    public void testLogicalAnd() {
//        QLDataType QLDataTypeTrue = QLDataType.createValue(Expression.DataType.BOOLEAN, "true");
//        QLDataType QLDataTypeFalse = QLDataType.createValue(Expression.DataType.BOOLEAN, "false");
//
//        Assert.assertEquals(QLDataTypeTrue.getType(), QLDataTypeFalse.getType());
//
//        // true && false -> false
//        QLDataType result = QLDataTypeTrue.and(QLDataTypeFalse);
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        // false && true -> false
//        result = QLDataTypeFalse.and(QLDataTypeTrue);
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        // false && false -> false
//        result = QLDataTypeFalse.and(QLDataTypeFalse);
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        // true && true -> true
//        result = QLDataTypeTrue.and(QLDataTypeTrue);
//        Assert.assertEquals(true, result.getBooleanValue());
//
//    }
//
//    @Test
//    public void testLogicalOr() {
//        QLDataType QLDataTypeTrue = QLDataType.createValue(Expression.DataType.BOOLEAN, "true");
//        QLDataType QLDataTypeFalse = QLDataType.createValue(Expression.DataType.BOOLEAN, "false");
//
//        Assert.assertEquals(QLDataTypeTrue.getType(), QLDataTypeFalse.getType());
//
//        // true || false -> true
//        QLDataType result = QLDataTypeTrue.or(QLDataTypeFalse);
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        // false || true -> true
//        result = QLDataTypeFalse.or(QLDataTypeTrue);
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        // false || false -> false
//        result = QLDataTypeFalse.or(QLDataTypeFalse);
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        // true || true -> true
//        result = QLDataTypeTrue.or(QLDataTypeTrue);
//        Assert.assertEquals(true, result.getBooleanValue());
//
//    }
//
//    @Test
//    public void testStringConcatenation() {
//        QLDataType val1 = QLDataType.createValue(Expression.DataType.STRING, "One");
//        QLDataType val2 = QLDataType.createValue(Expression.DataType.STRING, "Two");
//        QLDataType result = val1.add(val2);
//        
//        Assert.assertEquals("OneTwo", result.getStringValue());
//    }
//
//    @Test
//    public void testIntegerComparision() {
//        QLDataType val1 = QLDataType.createValue(Expression.DataType.INTEGER, "10");
//        QLDataTypeInteger val2 = new QLDataTypeInteger(BigInteger.valueOf( "7");
//
//        QLDataType result = val1.equals(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.notEquals(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        result = val1.lessThan(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.lessEqual(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.greaterThan(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        result = val1.greaterEqual(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//    }
//
//    @Test
//    public void testDecimalComparision() {
//        QLDataTypeDecimal val1 = new QLDataTypeDecimal(new BigDecimal("10.4");
//        QLDataTypeDecimal val2 = new QLDataTypeDecimal(new BigDecimal("7.4");
//
//        QLDataType result = val1.equals(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.notEquals(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        result = val1.lessThan(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.lessEqual(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(false, result.getBooleanValue());
//
//        result = val1.greaterThan(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//
//        result = val1.greaterEqual(val2);
//        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
//        Assert.assertEquals(true, result.getBooleanValue());
//    }
//

}
