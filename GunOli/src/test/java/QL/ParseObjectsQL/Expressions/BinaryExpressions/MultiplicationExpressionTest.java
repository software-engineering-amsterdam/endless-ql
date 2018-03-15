package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class MultiplicationExpressionTest {
    private MultiplicationExpression expressionTest;
    @Property
    public void evaluate(int left, int right) {
        IntegerConstant leftToTest = new IntegerConstant(left);
        IntegerConstant rightToTest = new IntegerConstant(right);

        expressionTest = new MultiplicationExpression(leftToTest,rightToTest);
        assertEquals((left * right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluate(double left, double right) {
        DecimalConstant leftToTest = new DecimalConstant( left);
        DecimalConstant rightToTest = new DecimalConstant( right);

        expressionTest = new MultiplicationExpression(leftToTest,rightToTest);
        assertEquals((left * right), expressionTest.evaluate().getValue());
    }

}