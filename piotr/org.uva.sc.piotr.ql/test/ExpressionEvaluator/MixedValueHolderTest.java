package ExpressionEvaluator;

import ast.model.expressions.Expression;
import gui.model.MixedValueHolder;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public final class MixedValueHolderTest {

    @Test
    public void testIncompatibleTypesExceptionMessage() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.STRING, "7");

        try {
            MixedValueHolder result = val1.add(val2);
        } catch (Exception e) {
            Assert.assertEquals("Types not compatible: INTEGER and STRING", e.getMessage());
        }
    }

    @Test
    public void testIntegerNegation() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(-10, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerAddition() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "7");
        MixedValueHolder result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(17, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerSubtraction() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "17");
        MixedValueHolder result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(-7, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerMultiplication() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "3");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "7");
        MixedValueHolder result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(21, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerDivision() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "3");
        MixedValueHolder result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(3, (long) result.getIntegerValue());
    }

    @Test
    public void testDecimalAddition() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "3.2");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "2.1");

        MixedValueHolder result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.3"), result.getDecimalValue());
    }

    @Test
    public void testDecimalSubtraction() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "43.16");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "3.15");

        MixedValueHolder result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.01"), result.getDecimalValue());
    }

    @Test
    public void testDecimalMultiplication() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "3.2");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "2.1");

        MixedValueHolder result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.72"), result.getDecimalValue());
    }

    @Test
    public void testDecimalDivision() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "6.72");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "2.1");

        MixedValueHolder result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "3.2");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "2");

        MixedValueHolder result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "43.16");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "3");

        MixedValueHolder result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.16"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "3.2");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "2");

        MixedValueHolder result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.4"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "6.72");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "2");

        MixedValueHolder result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.36"), result.getDecimalValue());
    }

    @Test
    public void testLogicalNegation() {
        MixedValueHolder mixedValueHolderTrue = MixedValueHolder.createValueHolder(Expression.DataType.BOOLEAN, "true");

        MixedValueHolder result = mixedValueHolderTrue.negate();
        Assert.assertEquals(result.getType(), mixedValueHolderTrue.getType());
        Assert.assertEquals(false, result.getBooleanValue());
    }

    @Test
    public void testLogicalAnd() {
        MixedValueHolder mixedValueHolderTrue = MixedValueHolder.createValueHolder(Expression.DataType.BOOLEAN, "true");
        MixedValueHolder mixedValueHolderFalse = MixedValueHolder.createValueHolder(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(mixedValueHolderTrue.getType(), mixedValueHolderFalse.getType());

        // true && false -> false
        MixedValueHolder result = mixedValueHolderTrue.and(mixedValueHolderFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && true -> false
        result = mixedValueHolderFalse.and(mixedValueHolderTrue);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && false -> false
        result = mixedValueHolderFalse.and(mixedValueHolderFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true && true -> true
        result = mixedValueHolderTrue.and(mixedValueHolderTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testLogicalOr() {
        MixedValueHolder mixedValueHolderTrue = MixedValueHolder.createValueHolder(Expression.DataType.BOOLEAN, "true");
        MixedValueHolder mixedValueHolderFalse = MixedValueHolder.createValueHolder(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(mixedValueHolderTrue.getType(), mixedValueHolderFalse.getType());

        // true || false -> true
        MixedValueHolder result = mixedValueHolderTrue.or(mixedValueHolderFalse);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || true -> true
        result = mixedValueHolderFalse.or(mixedValueHolderTrue);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || false -> false
        result = mixedValueHolderFalse.or(mixedValueHolderFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true || true -> true
        result = mixedValueHolderTrue.or(mixedValueHolderTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testStringConcatenation() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.STRING, "One");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.STRING, "Two");
        MixedValueHolder result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals("OneTwo", result.getStringValue());
    }

    @Test
    public void testIntegerComparision() {
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "10");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.INTEGER, "7");

        MixedValueHolder result = val1.equals(val2);
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
        MixedValueHolder val1 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "10.4");
        MixedValueHolder val2 = MixedValueHolder.createValueHolder(Expression.DataType.DECIMAL, "7.4");

        MixedValueHolder result = val1.equals(val2);
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
