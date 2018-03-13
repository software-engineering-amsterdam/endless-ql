package ExpressionEvaluator;

import ast.model.expressions.Expression;
import logic.evaluators.UniversalTypeValue;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public final class UniversalTypeValueTest {

    @Test
    public void testIncompatibleTypesExceptionMessage() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.STRING, "7");

        try {
            UniversalTypeValue result = val1.add(val2);
        } catch (Exception e) {
            Assert.assertEquals("Types not compatible: INTEGER and STRING", e.getMessage());
        }
    }

    @Test
    public void testIntegerNegation() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(-10, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerAddition() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "7");
        UniversalTypeValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(17, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerSubtraction() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "17");
        UniversalTypeValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(-7, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerMultiplication() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "3");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "7");
        UniversalTypeValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(21, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerDivision() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "3");
        UniversalTypeValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(3, (long) result.getIntegerValue());
    }

    @Test
    public void testDecimalAddition() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "3.2");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "2.1");

        UniversalTypeValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.3"), result.getDecimalValue());
    }

    @Test
    public void testDecimalSubtraction() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "43.16");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "3.15");

        UniversalTypeValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.01"), result.getDecimalValue());
    }

    @Test
    public void testDecimalMultiplication() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "3.2");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "2.1");

        UniversalTypeValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.72"), result.getDecimalValue());
    }

    @Test
    public void testDecimalDivision() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "6.72");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "2.1");

        UniversalTypeValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "3.2");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "2");

        UniversalTypeValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "43.16");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "3");

        UniversalTypeValue result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.16"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "3.2");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "2");

        UniversalTypeValue result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.4"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "6.72");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "2");

        UniversalTypeValue result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.36"), result.getDecimalValue());
    }

    @Test
    public void testLogicalNegation() {
        UniversalTypeValue universalTypeValueTrue = UniversalTypeValue.createValue(Expression.DataType.BOOLEAN, "true");

        UniversalTypeValue result = universalTypeValueTrue.negate();
        Assert.assertEquals(result.getType(), universalTypeValueTrue.getType());
        Assert.assertEquals(false, result.getBooleanValue());
    }

    @Test
    public void testLogicalAnd() {
        UniversalTypeValue universalTypeValueTrue = UniversalTypeValue.createValue(Expression.DataType.BOOLEAN, "true");
        UniversalTypeValue universalTypeValueFalse = UniversalTypeValue.createValue(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(universalTypeValueTrue.getType(), universalTypeValueFalse.getType());

        // true && false -> false
        UniversalTypeValue result = universalTypeValueTrue.and(universalTypeValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && true -> false
        result = universalTypeValueFalse.and(universalTypeValueTrue);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && false -> false
        result = universalTypeValueFalse.and(universalTypeValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true && true -> true
        result = universalTypeValueTrue.and(universalTypeValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testLogicalOr() {
        UniversalTypeValue universalTypeValueTrue = UniversalTypeValue.createValue(Expression.DataType.BOOLEAN, "true");
        UniversalTypeValue universalTypeValueFalse = UniversalTypeValue.createValue(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(universalTypeValueTrue.getType(), universalTypeValueFalse.getType());

        // true || false -> true
        UniversalTypeValue result = universalTypeValueTrue.or(universalTypeValueFalse);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || true -> true
        result = universalTypeValueFalse.or(universalTypeValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || false -> false
        result = universalTypeValueFalse.or(universalTypeValueFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true || true -> true
        result = universalTypeValueTrue.or(universalTypeValueTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testStringConcatenation() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.STRING, "One");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.STRING, "Two");
        UniversalTypeValue result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals("OneTwo", result.getStringValue());
    }

    @Test
    public void testIntegerComparision() {
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "10");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.INTEGER, "7");

        UniversalTypeValue result = val1.equals(val2);
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
        UniversalTypeValue val1 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "10.4");
        UniversalTypeValue val2 = UniversalTypeValue.createValue(Expression.DataType.DECIMAL, "7.4");

        UniversalTypeValue result = val1.equals(val2);
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
