package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static org.junit.Assert.*;

//@RunWith(JUnitQuickcheck.class)
public class AdditionExpressionTest {

    private IntegerConstant a = new IntegerConstant( 3);

    private DecimalConstant b = new DecimalConstant(5.0);
    private DecimalConstant c = new DecimalConstant(-5.0);
    private AdditionExpression positiveTest = new AdditionExpression(a, b);
    private AdditionExpression negativeTest = new AdditionExpression(a, c);


    @Test
    public void evaluate() {
        //Todo: Make this better, look at changes to AdditionExpression


        double posValue = Double.parseDouble(positiveTest.evaluate().getValue().toString());
        double posEXP = 8.00;
        double negValue = Double.parseDouble(negativeTest.evaluate().getValue().toString());
        double negEXP = -2.00;

        //double value = (double) ae.evaluate().getValue();
        //assertEquals(8.00, value, 0);

        assertEquals(negEXP, negValue, 0);
        assertEquals(posEXP, posValue, 0);
    }
    /*
    @Property
    public void evaluate(Expression autoA, Expression autoB) {
        AdditionExpression test  = new AdditionExpression(autoA, autoB);
        double expLeft = Double.parseDouble(autoA.toString());
        double expRight = Double.parseDouble(autoB.toString());

        assertEquals((expLeft + expRight), test.evaluate().getValue());
    }*/
}