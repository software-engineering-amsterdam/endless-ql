import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.Precision;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import expression.*;
import expression.variable.ExpressionVariableBoolean;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.ExpressionVariableNumber;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class ExpressionUnaryTest {

    @Property
    public void ExpressionUnaryNot(boolean value) {
        ANTLRTester tester = new ANTLRTester("!" + value);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionUnaryNot expectedExpression = new ExpressionUnaryNot(new ExpressionVariableBoolean(value));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionUnaryNegInteger(int value) {
        ANTLRTester tester = new ANTLRTester("-" + value);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableNumber(value + ""));
        assertEquals(expectedExpression, actualExpression);
    }

    @Property
    public void ExpressionUnaryNegDecimal(double value) {
        ANTLRTester tester = new ANTLRTester("-" + value);
        Expression actualExpression = tester.visitor.visit(tester.parser.expression());

        ExpressionUnaryNeg expectedExpression = new ExpressionUnaryNeg(new ExpressionVariableNumber(value + ""));
        assertEquals(expectedExpression, actualExpression);
    }
}
