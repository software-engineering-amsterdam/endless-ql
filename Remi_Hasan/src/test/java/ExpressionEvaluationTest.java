import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    public Object evaluateExpression(String expressionString) {
        ANTLRTester tester = new ANTLRTester(expressionString);
        Expression expression = tester.visitor.visit(tester.parser.expression());
        return expression.evaluate().getValue();
    }

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

    @Property
    public void ExpressionEvaluationNeg(int i) {
        Object result = evaluateExpression("-" + i);
        assertEquals(result, -i);
    }

}
