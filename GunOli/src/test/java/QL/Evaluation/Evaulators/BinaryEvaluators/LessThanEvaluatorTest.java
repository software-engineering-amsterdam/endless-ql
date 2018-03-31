package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
@RunWith(JUnitQuickcheck.class)
public class LessThanEvaluatorTest {
    LessThanEvaluator lessThanEvaluatorTest;
    @Property
    public void evaluateInt(int left, int right) {

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        lessThanEvaluatorTest = new LessThanEvaluator(leftToTest, rightToTest);

        boolean ans = left < right;

        assertEquals(ans, lessThanEvaluatorTest.evaluate().getValue());
    }
}