package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class AdditionExpressionTest {

    private IntegerConstant a = new IntegerConstant( 3);
    private DecimalConstant b = new DecimalConstant(5.0);
    private DecimalConstant c = new DecimalConstant(-5.0);
    private AdditionExpression positiveTest = new AdditionExpression(a, b);
    private AdditionExpression negativeTest = new AdditionExpression(a, c);

    /*@Test
    public void evaluate() {

        double posValue = positiveTest.evaluate().getValue();
        double posEXP = 8.00;
        double negValue = negativeTest.evaluate().getValue();
        double negEXP = -2.00;

        assertEquals(negEXP, negValue, 0);
        assertEquals(posEXP, posValue, 0);
    }*/
    @Property
    public void evaluate() {

    }
}