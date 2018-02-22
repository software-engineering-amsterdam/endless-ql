import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionArithmeticTest {

    @Property
    public void ExpressionArithmeticDivide(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        ANTLRTester tester = new ANTLRTester(left + " / " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticDivide expectedExpression = new ExpressionArithmeticDivide(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        if(!expectedExpression.equals(actualExpression)){
            System.out.println("");
        }
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticMultiply(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        ANTLRTester tester = new ANTLRTester(left + " * " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticMultiply expectedExpression = new ExpressionArithmeticMultiply(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSubtract(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        ANTLRTester tester = new ANTLRTester(left + " - " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());
        ExpressionArithmeticSubtract expectedExpression = new ExpressionArithmeticSubtract(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionArithmeticSum(@InRange(min = "-1000", max = "1000") int left, @InRange(min = "-1000", max = "1000") int right){
        ANTLRTester tester = new ANTLRTester(left + " + " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionArithmeticSum expectedExpression = new ExpressionArithmeticSum(new ExpressionVariableInteger(left), new ExpressionVariableInteger(right));
        assertEquals(expectedExpression, actualExpression);
    }
    
}
