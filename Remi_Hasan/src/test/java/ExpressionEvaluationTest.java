import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionEvaluationTest {

    @Property
    public void ExpressionEvaluationSum(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression expression = tester.visitor.visit(tester.parser.expression());
        assertEquals(expression.evaluate().get(), left + right);
    }

    @Property
    public void ExpressionEvaluationSum(double left, double right){
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression expression = tester.visitor.visit(tester.parser.expression());
        assertEquals(expression.evaluate().get(), left + right);
    }

}
