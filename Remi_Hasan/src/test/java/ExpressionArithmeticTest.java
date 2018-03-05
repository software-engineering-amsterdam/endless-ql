import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import expression.binary.ExpressionArithmeticDivide;
import expression.binary.ExpressionArithmeticMultiply;
import expression.binary.ExpressionArithmeticSubtract;
import expression.binary.ExpressionArithmeticSum;
import expression.variable.ExpressionVariableNumber;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionArithmeticTest {

    @Property
    public void ExpressionArithmeticDivide(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " / " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticDivide expectedExpression = new ExpressionArithmeticDivide(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticMultiply(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " * " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticMultiply expectedExpression = new ExpressionArithmeticMultiply(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSubtract(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " - " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticSubtract expectedExpression = new ExpressionArithmeticSubtract(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSum(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticSum expectedExpression = new ExpressionArithmeticSum(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

}