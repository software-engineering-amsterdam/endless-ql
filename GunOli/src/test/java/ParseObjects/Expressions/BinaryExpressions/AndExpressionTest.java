package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import static ParseObjects.Expressions.EvaluationType.Boolean;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.text.normalizer.NormalizerBase;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class AndExpressionTest {

    @Property
    public void evaluate(boolean left, boolean right){

        BooleanConstant leftConst = new BooleanConstant(left);
        BooleanConstant rightConst = new BooleanConstant(right);

        AndExpression toTest = new AndExpression(leftConst, rightConst);
        assertEquals((left && right),toTest.evaluate().getValue());

    }

}