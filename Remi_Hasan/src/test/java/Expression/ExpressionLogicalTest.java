package Expression;

import Parser.ANTLRTester;
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
        Expression actualExpression = ANTLRTester.expressionFromString(left + " && " + right);

        ExpressionLogicalAnd expectedExpression = new ExpressionLogicalAnd(null,
                new ExpressionVariableBoolean(null, left), new ExpressionVariableBoolean(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionLogicalOr(boolean left, boolean right) {
        Expression actualExpression = ANTLRTester.expressionFromString(left + " || " + right);

        ExpressionLogicalOr expectedExpression = new ExpressionLogicalOr(null,
                new ExpressionVariableBoolean(null, left), new ExpressionVariableBoolean(null, right));
        assertEquals(expectedExpression, actualExpression);
    }

}
