package QL.AST.Expressions.BinaryExpressions;

import QL.AST.Expressions.ExpressionConstants.DecimalConstant;
import QL.AST.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class SubtractExpressionTest {
    private SubtractExpression expressionTest;
    @Property
    public void evaluate(int left, int right) {
        IntegerConstant b = new IntegerConstant( left, 0);
        IntegerConstant c = new IntegerConstant( right, 0);

        expressionTest = new SubtractExpression(b,c, 0);
        assertEquals((left - right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluate(double left, double right) {
        DecimalConstant b = new DecimalConstant( left, 0);
        DecimalConstant c = new DecimalConstant( right, 0);

        expressionTest = new SubtractExpression(b,c, 0);
        assertEquals((left - right), expressionTest.evaluate().getValue());
    }

}