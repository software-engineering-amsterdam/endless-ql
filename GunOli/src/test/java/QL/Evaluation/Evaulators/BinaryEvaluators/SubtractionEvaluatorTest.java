package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class SubtractionEvaluatorTest {
    SubtractionEvaluator subtractionEvaluatorTest;
    @Property
    public void evaluateInt(int left, int right) {

        NumericValue lefttoTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        double ans = left *1.0 - right * 1.0;

        subtractionEvaluatorTest = new SubtractionEvaluator(lefttoTest, rightToTest);

        assertEquals(ans, subtractionEvaluatorTest.evaluate().getValue());

    }
}