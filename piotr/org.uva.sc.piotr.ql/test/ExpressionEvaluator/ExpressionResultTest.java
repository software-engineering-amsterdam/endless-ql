package ExpressionEvaluator;

import ast.model.expressions.Expression;
import ast.visitors.evaluators.ExpressionResult;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public final class ExpressionResultTest {

    @Test
    public void testIncompatibleTypesExceptionMessage() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.STRING, "7");

        try {
            ExpressionResult result = val1.add(val2);
        } catch (Exception e) {
            Assert.assertEquals("Types not compatible: INTEGER and STRING", e.getMessage());
        }
    }

    @Test
    public void testIntegerNegation() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult result = val1.negate();
        Assert.assertEquals(result.getType(), val1.getType());
        Assert.assertEquals(-10, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerAddition() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "7");
        ExpressionResult result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(17, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerSubtraction() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "17");
        ExpressionResult result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(-7, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerMultiplication() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "3");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "7");
        ExpressionResult result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(21, (long) result.getIntegerValue());
    }

    @Test
    public void testIntegerDivision() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "3");
        ExpressionResult result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(3, (long) result.getIntegerValue());
    }

    @Test
    public void testDecimalAddition() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "3.2");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "2.1");

        ExpressionResult result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.3"), result.getDecimalValue());
    }

    @Test
    public void testDecimalSubtraction() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "43.16");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "3.15");

        ExpressionResult result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.01"), result.getDecimalValue());
    }

    @Test
    public void testDecimalMultiplication() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "3.2");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "2.1");

        ExpressionResult result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.72"), result.getDecimalValue());
    }

    @Test
    public void testDecimalDivision() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "6.72");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "2.1");

        ExpressionResult result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalAddition() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "3.2");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "2");

        ExpressionResult result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("5.2"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalSubtraction() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "43.16");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "3");

        ExpressionResult result = val1.subtract(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("40.16"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalMultiplication() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "3.2");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "2");

        ExpressionResult result = val1.multiply(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("6.4"), result.getDecimalValue());
    }

    @Test
    public void testIntegerCastToDecimalDivision() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "6.72");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "2");

        ExpressionResult result = val1.divide(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals(new BigDecimal("3.36"), result.getDecimalValue());
    }

    @Test
    public void testLogicalNegation() {
        ExpressionResult expressionResultTrue = ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "true");

        ExpressionResult result = expressionResultTrue.negate();
        Assert.assertEquals(result.getType(), expressionResultTrue.getType());
        Assert.assertEquals(false, result.getBooleanValue());
    }

    @Test
    public void testLogicalAnd() {
        ExpressionResult expressionResultTrue = ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "true");
        ExpressionResult expressionResultFalse = ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(expressionResultTrue.getType(), expressionResultFalse.getType());

        // true && false -> false
        ExpressionResult result = expressionResultTrue.and(expressionResultFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && true -> false
        result = expressionResultFalse.and(expressionResultTrue);
        Assert.assertEquals(false, result.getBooleanValue());

        // false && false -> false
        result = expressionResultFalse.and(expressionResultFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true && true -> true
        result = expressionResultTrue.and(expressionResultTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testLogicalOr() {
        ExpressionResult expressionResultTrue = ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "true");
        ExpressionResult expressionResultFalse = ExpressionResult.createExpressionResult(Expression.DataType.BOOLEAN, "false");

        Assert.assertEquals(expressionResultTrue.getType(), expressionResultFalse.getType());

        // true || false -> true
        ExpressionResult result = expressionResultTrue.or(expressionResultFalse);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || true -> true
        result = expressionResultFalse.or(expressionResultTrue);
        Assert.assertEquals(true, result.getBooleanValue());

        // false || false -> false
        result = expressionResultFalse.or(expressionResultFalse);
        Assert.assertEquals(false, result.getBooleanValue());

        // true || true -> true
        result = expressionResultTrue.or(expressionResultTrue);
        Assert.assertEquals(true, result.getBooleanValue());

    }

    @Test
    public void testStringConcatenation() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.STRING, "One");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.STRING, "Two");
        ExpressionResult result = val1.add(val2);
        Assert.assertEquals(val1.getType(), val2.getType());
        Assert.assertEquals("OneTwo", result.getStringValue());
    }

    @Test
    public void testIntegerComparision() {
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "10");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.INTEGER, "7");

        ExpressionResult result = val1.equals(val2);
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
        ExpressionResult val1 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "10.4");
        ExpressionResult val2 = ExpressionResult.createExpressionResult(Expression.DataType.DECIMAL, "7.4");

        ExpressionResult result = val1.equals(val2);
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
