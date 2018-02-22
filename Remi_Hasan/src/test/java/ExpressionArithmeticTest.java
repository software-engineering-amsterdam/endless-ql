import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionArithmeticTest {

    @Property
    public void ExpressionArithmeticDivide(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " / " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticDivide expectedExpression =
                new ExpressionArithmeticDivide(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticMultiply(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " * " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticMultiply expectedExpression =
                new ExpressionArithmeticMultiply(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSubtract(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " - " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticSubtract expectedExpression =
                new ExpressionArithmeticSubtract(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSum(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticSum expectedExpression =
                new ExpressionArithmeticSum(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }
    
}
