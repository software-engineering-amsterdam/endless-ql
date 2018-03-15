package ql.Expression;

import ql.QLTestUtilities;
import ql.evaluation.value.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExpressionPrecedenceTest {
    @Test
    public void plusMultiplyPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("5 + 6 * 3");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(23));
    }

    @Test
    public void multiplyPlusPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("6 * 3 + 5");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(23));
    }

    @Test
    public void multiplyPlusDividePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("6 * 3 + 40 / 4");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(28));
    }

    @Test
    public void minusDividePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("11 - 30 / 3");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(1));
    }

    @Test
    public void divideMinusPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("30 / 3 - 11");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(-1));
    }

    @Test
    public void parenthesesPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("(5 + 6) * 3");
        assertEquals(evaluatedExpression.getIntValue(), Integer.valueOf(33));
    }

    @Test
    public void expressionComparisonPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("3 * 4 > 3 * 4 - 1");
        assertEquals(evaluatedExpression.getBooleanValue(), true);
    }

    @Test
    public void expressionEqualsPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("3 * 4 == 3 * 4 - 1 + 1");
        assertEquals(evaluatedExpression.getBooleanValue(), true);
    }

    @Test
    public void andPrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("4 > 3 && 8 == 8");
        assertEquals(evaluatedExpression.getBooleanValue(), true);
    }

    @Test
    public void orAndTruePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("3 > 4 && 8 == 8 || 4 > 3");
        assertEquals(evaluatedExpression.getBooleanValue(), true);
    }

    @Test
    public void andOrTruePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("4 > 3 || 8 == 8 && 3 > 4");
        assertEquals(evaluatedExpression.getBooleanValue(), true);
    }

    @Test
    public void orAndFalsePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("3 > 4 && 8 == 8 || 3 > 4");
        assertEquals(evaluatedExpression.getBooleanValue(), false);
    }

    @Test
    public void andOrFalsePrecedence() {
        Value evaluatedExpression = QLTestUtilities.evaluateExpression("3 > 4 || 8 == 8 && 3 > 4");
        assertEquals(evaluatedExpression.getBooleanValue(), false);
    }
}
