package ParseObjectsQL.Expressions.BinaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class OrExpressionTest {

    @Property
    public void evaluate(boolean left, boolean right){

        BooleanConstant leftConst = new BooleanConstant(left);
        BooleanConstant rightConst = new BooleanConstant(right);

        OrExpression expressionTest = new OrExpression(leftConst, rightConst);
        assertEquals((left || right), expressionTest.evaluate().getValue());

    }

}