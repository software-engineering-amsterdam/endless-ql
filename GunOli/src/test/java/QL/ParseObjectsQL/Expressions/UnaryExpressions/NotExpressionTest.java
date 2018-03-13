package QL.ParseObjectsQL.Expressions.UnaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class NotExpressionTest {
    NotExpression expressionTest;
    @Property
    public void evaluate(boolean value) {
        BooleanConstant valueToTest = new BooleanConstant(value);
        expressionTest = new NotExpression(valueToTest);

        assertEquals(!value, expressionTest.evaluate().getValue());
    }
}