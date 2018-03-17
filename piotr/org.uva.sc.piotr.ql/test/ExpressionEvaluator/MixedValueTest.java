package ExpressionEvaluator;

import ast.model.expressions.Expression;
import logic.type.MixedValue;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public final class MixedValueTest {

    @Test
    public void testIncompatibleTypesExceptionMessage() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "10");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.STRING, "7");

        try {
            MixedValue result = val1.add(val2);
        } catch (Exception e) {
            Assert.assertEquals("Types not compatible: INTEGER and STRING", e.getMessage());
        }
    }

    @Test
    public void testIntegerNegation() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "10");
        MixedValue result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(-10, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerAddition() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "10");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "7");
        MixedValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(17, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerSubtraction() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "10");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "17");
        MixedValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(-7, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerMultiplication() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "3");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "7");
        MixedValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(21, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerDivision() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "9");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "3");
        MixedValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(3, (long) result.getIntegerValue());
    }

    @Test
    public void testDecimalAddition() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "3.2");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.DECIMAL, "2.1");

        MixedValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.3"), result.getDecimalValue());
    }

    @Test
    public void testDecimalSubtraction() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "43.16");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.DECIMAL, "3.15");

        MixedValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.01"), result.getDecimalValue());
    }

    @Test
    public void testDecimalMultiplication() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "3.2");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.DECIMAL, "2.1");

        MixedValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.72"), result.getDecimalValue());
    }

    @Test
    public void testDecimalDivision() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "6.72");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.DECIMAL, "2.1");

        MixedValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "3.2");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "2");

        MixedValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "43.16");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "3");

        MixedValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.16"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "3.2");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "2");

        MixedValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.4"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "6.72");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "2");

        MixedValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.36"), result.getDecimalValue());
    }

    @Test
    public void testLogicalNegation() {
        MixedValue mixedValueTrue = MixedValue.createValue(Expression.DataType.BOOLEAN, "true");

        MixedValue result = mixedValueTrue.negate();
        Assert.assertEquals(result.getType(), mixedValueTrue.getType());
        Assert.assertEquals(false, result.getBooleanValue());
    }

    @Test
    public void testLogicalAnd() {
        MixedValue mixedValueTrue = MixedValue.createValue(Expression.DataType.BOOLEAN, "true");
        MixedValue mixedValueFalse = MixedValue.createValue(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(mixedValueTrue.getType(), mixedValueFalse.getType());

        // true && false -> false
        MixedValue result = mixedValueTrue.and(mixedValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && true -> false
        result = mixedValueFalse.and(mixedValueTrue);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && false -> false
        result = mixedValueFalse.and(mixedValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true && true -> true
        result = mixedValueTrue.and(mixedValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testLogicalOr() {
        MixedValue mixedValueTrue = MixedValue.createValue(Expression.DataType.BOOLEAN, "true");
        MixedValue mixedValueFalse = MixedValue.createValue(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(mixedValueTrue.getType(), mixedValueFalse.getType());

        // true || false -> true
        MixedValue result = mixedValueTrue.or(mixedValueFalse);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || true -> true
        result = mixedValueFalse.or(mixedValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || false -> false
        result = mixedValueFalse.or(mixedValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true || true -> true
        result = mixedValueTrue.or(mixedValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testStringConcatenation() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.STRING, "One");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.STRING, "Two");
        MixedValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals("OneTwo", result.getStringValue());
    }

    @Test
    public void testIntegerComparision() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.INTEGER, "10");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.INTEGER, "7");

        MixedValue result = val1.equals(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.notEquals(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());

        result = val1.lessThan(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.lessEqual(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.greaterThan(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());

        result = val1.greaterEqual(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());
    }

    @Test
    public void testDecimalComparision() {
        MixedValue val1 = MixedValue.createValue(Expression.DataType.DECIMAL, "10.4");
        MixedValue val2 = MixedValue.createValue(Expression.DataType.DECIMAL, "7.4");

        MixedValue result = val1.equals(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.notEquals(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());

        result = val1.lessThan(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.lessEqual(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(false, result.getBooleanValue());

        result = val1.greaterThan(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());

        result = val1.greaterEqual(val2);
        Assert.assertEquals(Expression.DataType.BOOLEAN, result.getType());
        Assert.assertEquals(true, result.getBooleanValue());
    }

    // TODO: Exceptions tests
}
