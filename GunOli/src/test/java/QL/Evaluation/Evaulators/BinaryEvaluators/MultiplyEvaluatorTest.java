package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class MultiplyEvaluatorTest {
    MultiplyEvaluator multiplyEvaluatortest;
    @Property
    public void evaluateInt(int left, int right) {

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        double ans = (left * 1.0) * (right * 1.0);

        multiplyEvaluatortest = new MultiplyEvaluator(leftToTest, rightToTest);

        assertEquals(ans, multiplyEvaluatortest.evaluate().getValue());
    }
}