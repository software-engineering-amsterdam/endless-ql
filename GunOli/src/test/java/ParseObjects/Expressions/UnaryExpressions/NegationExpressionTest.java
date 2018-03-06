package ParseObjects.Expressions.UnaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class NegationExpressionTest {
    NegationExpression expressionTest;
    @Property
    public void evaluate(int value) {
        IntegerConstant valueToTest = new IntegerConstant(value);

        expressionTest = new NegationExpression(valueToTest);

        assertEquals((value*(-1)), expressionTest.evaluate().getValue());
    }
    @Property
    public void evaluate(double value) {
        DecimalConstant valueToTest = new DecimalConstant(value);

        expressionTest = new NegationExpression(valueToTest);

        assertEquals((value*(-1)), expressionTest.evaluate().getValue());
    }
}