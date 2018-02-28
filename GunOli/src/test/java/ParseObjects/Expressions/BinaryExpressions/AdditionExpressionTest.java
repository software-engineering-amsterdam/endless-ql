package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

import org.junit.Test;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static org.junit.Assert.*;

public class AdditionExpressionTest {

    private IntegerConstant a = new IntegerConstant( 3);
    private DecimalConstant b = new DecimalConstant(5.0);
    private AdditionExpression ae = new AdditionExpression(a, b);

    @Test
    public void returnType() {
        assertEquals(Decimal, ae.returnType());

    }

    @Test
    public void evaluate() {

        double value = ae.evaluate().getValue();
        assertEquals(8.00, value, 0);
    }

    @Test
    public void isArithmetic() {
        assertEquals(true,ae.isArithmetic());

    }

}