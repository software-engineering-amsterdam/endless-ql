package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class OrExpressionTest {

    @Property
    public void evaluate(boolean left, boolean right){

        BooleanConstant leftConst = new BooleanConstant(left, 0);
        BooleanConstant rightConst = new BooleanConstant(right, 0);

        OrExpression expressionTest = new OrExpression(leftConst, rightConst, 0);
        assertEquals((left || right), expressionTest.evaluate().getValue());

    }

}