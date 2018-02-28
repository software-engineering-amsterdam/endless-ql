package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import static ParseObjects.Expressions.EvaluationType.Boolean;
import org.junit.Test;

import static org.junit.Assert.*;

public class AndExpressionTest {

    private BooleanConstant a = new BooleanConstant(true) ;
    private BooleanConstant b = new BooleanConstant( true);
    AndExpression toTest = new AndExpression(a,b);

    @Test
    public void returnType() {
        assertEquals(Boolean,toTest.returnType());
    }

    @Test
    public void evaluate() {
        assertEquals(true, toTest.evaluate().getValue());
    }

    @Test
    public void isLogical() {
        boolean ans = true;
        assertEquals(ans,toTest.isLogical());
    }
}