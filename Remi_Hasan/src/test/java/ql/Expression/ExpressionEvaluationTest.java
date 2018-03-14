package ql.Expression;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import ql.QLTestUtilities;
import ql.evaluation.value.Value;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    private static double DELTA = 0.001;

    // Prevents E notation to be in a double's string representation, which would be parsed incorrectly
    public String doubleString(double value) {
        return new BigDecimal(value).toPlainString();
    }

    // Sum

    @Property
    public void ExpressionEvaluationSum(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " + " + right);
        assertEquals(Integer.valueOf(left + right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSum(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSum(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    // Subtract

    @Property
    public void ExpressionEvaluationSub(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " - " + right);
        assertEquals(Integer.valueOf(left - right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSub(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSub(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    // Multiply

    @Property
    public void ExpressionEvaluationMul(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " * " + right);
        assertEquals(Integer.valueOf(left * right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationMul(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationMul(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    // Divide

    @Property
    public void ExpressionEvaluationDiv(int left, int right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(left + " / " + right);
        assertEquals(Integer.valueOf(left / right), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationDiv(double left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationDiv(int left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(left + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationNeg(int i) {
        Value result = QLTestUtilities.evaluateExpression("-" + i);
        assertEquals(Integer.valueOf(-1 * i), result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationAnd(boolean left, boolean right) {
        Value result = QLTestUtilities.evaluateExpression(left + " && " + right);
        assertEquals(left && right, result.getBooleanValue());
    }

    @Property
    public void ExpressionEvaluationOr(boolean left, boolean right) {
        Value result = QLTestUtilities.evaluateExpression(left + " || " + right);
        assertEquals(left || right, result.getBooleanValue());
    }

}
