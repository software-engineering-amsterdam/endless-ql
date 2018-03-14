package ql.Expression;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import ql.QLTestUtilities;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableInteger;

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
    public void evaluateSum(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " + " + right);
        assertEquals(Integer.valueOf(left + right), result.getIntValue());
    }

    @Property
    public void evaluateSum(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void evaluateSum(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " + " + doubleString(right));
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    // Subtract

    @Property
    public void evaluateSub(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " - " + right);
        assertEquals(Integer.valueOf(left - right), result.getIntValue());
    }

    @Property
    public void evaluateSub(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void evaluateSub(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " - " + doubleString(right));
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    // Multiply

    @Property
    public void evaluateMul(int left, int right) {
        Value result = QLTestUtilities.evaluateExpression(left + " * " + right);
        assertEquals(Integer.valueOf(left * right), result.getIntValue());
    }

    @Property
    public void evaluateMul(double left, double right) {
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void evaluateMul(int left, double right) {
        Value result = QLTestUtilities.evaluateExpression(left + " * " + doubleString(right));
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    // Divide

    @Property
    public void evaluateDiv(int left, int right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(left + " / " + right);
        assertEquals(Integer.valueOf(left / right), result.getIntValue());
    }

    @Property
    public void evaluateDiv(double left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(doubleString(left) + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void evaluateDiv(int left, double right) {
        assumeThat(right, not(equalTo(0)));
        Value result = QLTestUtilities.evaluateExpression(left + " / " + doubleString(right));
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void evaluateNeg(int i) {
        Value result = QLTestUtilities.evaluateExpression("-" + i);
        assertEquals(Integer.valueOf(-1 * i), result.getIntValue());
    }

    @Property
    public void evaluateAnd(boolean left, boolean right) {
        Value result = QLTestUtilities.evaluateExpression(left + " && " + right);
        assertEquals(left && right, result.getBooleanValue());
    }

    @Property
    public void evaluateOr(boolean left, boolean right) {
        Value result = QLTestUtilities.evaluateExpression(left + " || " + right);
        assertEquals(left || right, result.getBooleanValue());
    }

    @Test
    public void evaluateReference() {
        Expression expression = QLTestUtilities.expressionFromString("2 + someInteger + 3");

        SymbolTable symbolTable = new SymbolTable();
        symbolTable.setExpression("someInteger", new ExpressionVariableInteger(null, 4));
        
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
        assertEquals(Integer.valueOf(9), interpreterVisitor.visit(expression).getIntValue());
    }

}
