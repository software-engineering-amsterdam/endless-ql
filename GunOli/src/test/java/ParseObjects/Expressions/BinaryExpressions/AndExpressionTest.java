package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import static ParseObjects.Expressions.EvaluationType.Boolean;
import org.junit.Test;

import static org.junit.Assert.*;

public class AndExpressionTest {

    private BooleanConstant a = new BooleanConstant(true) ;
    private BooleanConstant b = new BooleanConstant( false);

    private AndExpression trueToTest = new AndExpression(a, a);
    private AndExpression falseToTest = new AndExpression(b, b);
    private AndExpression trueAndFalseToTest = new AndExpression(a,b);



    @Test
    public void evaluate() {
        assertEquals(false, falseToTest.evaluate().getValue());
        assertEquals(false,trueAndFalseToTest.evaluate().getValue());
        assertEquals(true, trueToTest.evaluate().getValue());
    }


}