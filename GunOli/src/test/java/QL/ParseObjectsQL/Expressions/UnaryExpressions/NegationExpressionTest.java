package QL.ParseObjectsQL.Expressions.UnaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
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