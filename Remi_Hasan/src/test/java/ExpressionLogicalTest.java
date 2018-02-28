import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.Expression;
import expression.binary.ExpressionLogicalAnd;
import expression.binary.ExpressionLogicalOr;
import expression.constant.ExpressionVariableBoolean;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(JUnitQuickcheck.class)
public class ExpressionLogicalTest {

    @Property
    public void ExpressionLogicalAnd(boolean left, boolean right) {
        ANTLRTester tester = new ANTLRTester(left + " && " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionLogicalAnd expectedExpression = new ExpressionLogicalAnd(new ExpressionVariableBoolean(left), new ExpressionVariableBoolean(right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionLogicalOr(boolean left, boolean right) {
        ANTLRTester tester = new ANTLRTester(left + " || " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionLogicalOr expectedExpression = new ExpressionLogicalOr(new ExpressionVariableBoolean(left), new ExpressionVariableBoolean(right));
        assertEquals(expectedExpression, actualExpression);
    }

}
