package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class OrEvaluatorTest {
    OrEvaluator orEvaluatorTest;
    @Property
    public void evaluateInt(boolean left, boolean right) {

        BooleanValue leftToTest = new BooleanValue(left);
        BooleanValue rightToTest = new BooleanValue(right);

        orEvaluatorTest = new OrEvaluator(leftToTest, rightToTest);
        boolean ans = (left || right);

        assertEquals(ans, orEvaluatorTest.evaluate().getValue());


    }
}