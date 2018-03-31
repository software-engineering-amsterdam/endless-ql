package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class LessEqualEvaluatorTest {
    LessEqualEvaluator lessEqualEvaluatorTest;
    @Property
    public void evaluateInt(int left, int right) {

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        boolean ans = left <= right;

        lessEqualEvaluatorTest = new LessEqualEvaluator(leftToTest, rightToTest);

        assertEquals(ans, lessEqualEvaluatorTest.evaluate().getValue());

    }

    @Property
    public void evaluateDouble(double left, double right) {

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        boolean ans = left <= right;

        lessEqualEvaluatorTest = new LessEqualEvaluator(leftToTest, rightToTest);

        assertEquals(ans, lessEqualEvaluatorTest.evaluate().getValue());

    }
}