package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class DivisionExpressionTest {

    private DivisionExpression expressionTest;
    @Property
    public void evaluate(int autoA, int autoB) {
        IntegerConstant b = new IntegerConstant( autoA);
        IntegerConstant c = new IntegerConstant( autoB);

        expressionTest  = new DivisionExpression(b,c);
        assertEquals((autoA / autoB), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluate(double autoA, double autoB) {
        DecimalConstant b = new DecimalConstant( autoA);
        DecimalConstant c = new DecimalConstant( autoB);

        expressionTest  = new DivisionExpression(b,c);
        assertEquals((autoA / autoB), expressionTest.evaluate().getValue());

    }

}