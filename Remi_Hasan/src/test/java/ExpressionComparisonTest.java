import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import model.expression.Expression;
import model.expression.binary.*;
import model.expression.variable.ExpressionVariableInteger;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionComparisonTest {

    @Property
    public void ExpressionComparisonEq(int left, int right) {
        ANTLRTester tester = new ANTLRTester(left + " == " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonEq expectedExpression = new ExpressionComparisonEq(null,
                new ExpressionVariableInteger(null, left), new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGE(int left, int right) {
        ANTLRTester tester = new ANTLRTester(left + " >= " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonGE expectedExpression = new ExpressionComparisonGE(null,
                new ExpressionVariableInteger(null, left), new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonGT(int left, int right) {
        ANTLRTester tester = new ANTLRTester(left + " > " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonGT expectedExpression = new ExpressionComparisonGT(null,
                new ExpressionVariableInteger(null, left), new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLE(int left, int right) {
        ANTLRTester tester = new ANTLRTester(left + " <= " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonLE expectedExpression = new ExpressionComparisonLE(null,
                new ExpressionVariableInteger(null, left), new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionComparisonLT(int left, int right) {
        ANTLRTester tester = new ANTLRTester(left + " < " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionComparisonLT expectedExpression = new ExpressionComparisonLT(null,
                new ExpressionVariableInteger(null, left), new ExpressionVariableInteger(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

}
