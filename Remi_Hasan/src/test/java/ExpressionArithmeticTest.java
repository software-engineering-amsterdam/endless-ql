import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.Expression;
import expression.ExpressionVariableInteger;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class ExpressionArithmeticTest {

    @Property
    public void ExpressionArithmeticDivide(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        String input = left + " / " + right;
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(left / right);

        ExpressionArithhmeticBase(input, expectedExpression);
    }

    @Property
    public void ExpressionArithmeticDivideByZero(int left){
        boolean exceptionThrown = false;
        try {
            ExpressionArithmeticDivide(left, 0);
        } catch(ArithmeticException e){
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    @Property
    public void ExpressionArithmeticMultiply(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        String input = left + " * " + right;
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(left * right);

        ExpressionArithhmeticBase(input, expectedExpression);
    }

    @Property
    public void ExpressionArithmeticSubtract(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        String input = left + " - " + right;
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(left - right);

        ExpressionArithhmeticBase(input, expectedExpression);
    }

    @Property
    public void ExpressionArithmeticSum(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        String input = left + " + " + right;
        ExpressionVariableInteger expectedExpression = new ExpressionVariableInteger(left + right);

        ExpressionArithhmeticBase(input, expectedExpression);
    }

    public void ExpressionArithhmeticBase(String input, ExpressionVariableInteger expectedExpression){
        ANTLRTester tester = new ANTLRTester(input);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        assertEquals(expectedExpression.evaluate().get(), actualExpression.evaluate().get());
    }
    
}
