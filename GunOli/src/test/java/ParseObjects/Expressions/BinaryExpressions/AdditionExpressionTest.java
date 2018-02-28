package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

import org.junit.Test;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static org.junit.Assert.*;

public class AdditionExpressionTest {

    @Test
    public void returnType() {
        //ToDo:Implement
    }

    @Test
    public void evaluate() {
        IntegerConstant a = new IntegerConstant( 3);
        DecimalConstant b = new DecimalConstant(5.0);
        AdditionExpression ae = new AdditionExpression(a, b);
        double value = ae.evaluate().getValue();
        assertEquals(8.00, value, 0);
    }

    @Test
    public void isArithmetic() {
        //ToDo:Implement
    }

}