import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import expression.variable.ExpressionVariable;
import expression.variable.ExpressionVariableNumber;
import org.hamcrest.core.IsEqual;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    private static double DELTA = 0.001;

    public ExpressionVariableNumber evaluateExpression(String expressionString) {
        ANTLRTester tester = new ANTLRTester(expressionString);
        Expression expression = tester.visitor.visit(tester.parser.expression());

        Expression evaluated = expression.evaluate();
        assertEquals(evaluated.getReturnType(), ReturnType.NUMBER);

        return (ExpressionVariableNumber) evaluated;
    }

    // Sum

    @Property
    public void ExpressionEvaluationSum(int left, int right) {
        ExpressionVariableNumber result = evaluateExpression(left + " + " + right);
        assertEquals(left + right, result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSum(double left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " + " + right);
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSum(int left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " + " + right);
        assertEquals(left + right, result.getDecimalValue(), DELTA);
    }

    // Subtract

    @Property
    public void ExpressionEvaluationSub(int left, int right) {
        ExpressionVariableNumber result = evaluateExpression(left + " - " + right);
        assertEquals(left - right, result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationSub(double left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " - " + right);
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationSub(int left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " - " + right);
        assertEquals(left - right, result.getDecimalValue(), DELTA);
    }

    // Multiply

    @Property
    public void ExpressionEvaluationMul(int left, int right) {
        ExpressionVariableNumber result = evaluateExpression(left + " * " + right);
        assertEquals(left * right, result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationMul(double left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " * " + right);
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationMul(int left, double right) {
        ExpressionVariableNumber result = evaluateExpression(left + " * " + right);
        assertEquals(left * right, result.getDecimalValue(), DELTA);
    }

    // Divide

    @Property
    public void ExpressionEvaluationDiv(int left, int right) {
        assumeThat(right, not(equalTo(0)));
        ExpressionVariableNumber result = evaluateExpression(left + " / " + right);
        assertEquals(left / right, result.getIntValue());
    }

    @Property
    public void ExpressionEvaluationDiv(double left, double right) {
        assumeThat(right, not(equalTo(0)));
        ExpressionVariableNumber result = evaluateExpression(left + " / " + right);
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationDiv(int left, double right) {
        assumeThat(right, not(equalTo(0)));
        ExpressionVariableNumber result = evaluateExpression(left + " / " + right);
        assertEquals(left / right, result.getDecimalValue(), DELTA);
    }

    @Property
    public void ExpressionEvaluationNeg(int i) {
        ExpressionVariableNumber result = evaluateExpression("-" + i);
        assertEquals(-i, result.getIntValue());
    }

}
