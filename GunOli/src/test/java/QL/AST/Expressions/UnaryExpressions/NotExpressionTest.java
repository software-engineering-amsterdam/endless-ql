package QL.AST.Expressions.UnaryExpressions;

import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class NotExpressionTest {
    NotExpression expressionTest;
    @Property
    public void evaluate(boolean value) {
        BooleanConstant valueToTest = new BooleanConstant(value, 0);
        expressionTest = new NotExpression(valueToTest, 0);

        assertEquals(!value, expressionTest.evaluate().getValue());
    }
}