import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import model.expression.Expression;
import model.expression.binary.ExpressionLogicalAnd;
import model.expression.binary.ExpressionLogicalOr;
import model.expression.variable.ExpressionVariableBoolean;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(JUnitQuickcheck.class)
public class ExpressionLogicalTest {

    @Property
    public void ExpressionLogicalAnd(boolean left, boolean right) {
        ANTLRTester tester = new ANTLRTester(left + " && " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionLogicalAnd expectedExpression = new ExpressionLogicalAnd(null,
                new ExpressionVariableBoolean(null, left), new ExpressionVariableBoolean(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionLogicalOr(boolean left, boolean right) {
        ANTLRTester tester = new ANTLRTester(left + " || " + right);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionLogicalOr expectedExpression = new ExpressionLogicalOr(null,
                new ExpressionVariableBoolean(null, left), new ExpressionVariableBoolean(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

}
