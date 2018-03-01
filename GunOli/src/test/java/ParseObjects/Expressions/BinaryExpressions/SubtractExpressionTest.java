package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class SubtractExpressionTest {
    private SubtractExpression expressionTest;
    @Property
    public void evaluate(int left, int right) {
        IntegerConstant b = new IntegerConstant( left);
        IntegerConstant c = new IntegerConstant( right);

        expressionTest = new SubtractExpression(b,c);
        assertEquals((left - right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluate(double left, double right) {
        DecimalConstant b = new DecimalConstant( left);
        DecimalConstant c = new DecimalConstant( right);

        expressionTest = new SubtractExpression(b,c);
        assertEquals((left - right), expressionTest.evaluate().getValue());
    }

}