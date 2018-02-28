import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import org.hamcrest.core.IsEqual;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    public Object evaluateExpression(String expressionString) {
        ANTLRTester tester = new ANTLRTester(expressionString);
        Expression expression = tester.visitor.visit(tester.parser.expression());
        return expression.evaluate().getValue();
    }

    // Sum

    @Property
    public void ExpressionEvaluationSum(int left, int right) {
        Object result = evaluateExpression(left + " + " + right);
        assertEquals(result, left + right);
    }

    @Property
    public void ExpressionEvaluationSum(double left, double right) {
        Object result = evaluateExpression(left + " + " + right);
        assertEquals(result, left + right);
    }

    @Property
    public void ExpressionEvaluationSum(int left, double right) {
        Object result = evaluateExpression(left + " + " + right);
        assertEquals(result, left + right);
    }

    // Subtract

    @Property
    public void ExpressionEvaluationSub(int left, int right) {
        Object result = evaluateExpression(left + " - " + right);
        assertEquals(result, left - right);
    }

    @Property
    public void ExpressionEvaluationSub(double left, double right) {
        Object result = evaluateExpression(left + " - " + right);
        assertEquals(result, left - right);
    }

    @Property
    public void ExpressionEvaluationSub(int left, double right) {
        Object result = evaluateExpression(left + " - " + right);
        assertEquals(result, left - right);
    }

    // Multiply

    @Property
    public void ExpressionEvaluationMul(int left, int right) {
        Object result = evaluateExpression(left + " * " + right);
        assertEquals(result, left * right);
    }

    @Property
    public void ExpressionEvaluationMul(double left, double right) {
        Object result = evaluateExpression(left + " * " + right);
        assertEquals(result, left * right);
    }

    @Property
    public void ExpressionEvaluationMul(int left, double right) {
        Object result = evaluateExpression(left + " * " + right);
        assertEquals(result, left * right);
    }

    // Divide

    @Property
    public void ExpressionEvaluationDiv(int left, int right) {
        assumeThat(right, not(equalTo(0)));
        Object result = evaluateExpression(left + " / " + right);
        assertEquals(result, left / right);
    }

    @Property
    public void ExpressionEvaluationDiv(double left, double right) {
        assumeThat(right, not(equalTo(0)));
        Object result = evaluateExpression(left + " / " + right);
        assertEquals(result, left / right);
    }

    @Property
    public void ExpressionEvaluationDiv(int left, double right) {
        assumeThat(right, not(equalTo(0)));
        Object result = evaluateExpression(left + " / " + right);
        assertEquals(result, left / right);
    }

    @Property
    public void ExpressionEvaluationNeg(int i) {
        Object result = evaluateExpression("-" + i);
        assertEquals(result, -i);
    }

}
