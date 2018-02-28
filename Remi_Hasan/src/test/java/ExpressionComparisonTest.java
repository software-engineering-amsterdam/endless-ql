import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import expression.binary.*;
import expression.variable.ExpressionVariableNumber;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionComparisonTest {

    @Property
    public void ExpressionComparisonEq(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " == " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonEq expectedExpression = new ExpressionComparisonEq(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGE(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " >= " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonGE expectedExpression = new ExpressionComparisonGE(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGT(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " > " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonGT expectedExpression = new ExpressionComparisonGT(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLE(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " <= " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonLE expectedExpression = new ExpressionComparisonLE(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLT(int left, int right){
        ANTLRTester tester = new ANTLRTester(left + " < " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonLT expectedExpression = new ExpressionComparisonLT(new ExpressionVariableNumber(left), new ExpressionVariableNumber(right));
        assertEquals(expectedExpression, actualExpression);
    }

}
