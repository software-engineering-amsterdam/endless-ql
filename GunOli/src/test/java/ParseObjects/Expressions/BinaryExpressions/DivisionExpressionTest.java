package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import org.junit.Test;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static org.junit.Assert.*;

public class DivisionExpressionTest {

    private DecimalConstant a = new DecimalConstant(10.00);
    private DecimalConstant b = new DecimalConstant(2.00);
    private DivisionExpression toTest = new DivisionExpression(a,b);

    @Test
    public void returnType() {
        assertEquals(Decimal, toTest.returnType());
    }

    @Test
    public void evaluate() {
        //Double test = toTest.evaluate().getValue();
        //Double expected = 5.00;
        //assertEquals(expected, test);
    }

    @Test
    public void isArithmetic() {
        assertEquals(true,toTest.isArithmetic());
    }
}