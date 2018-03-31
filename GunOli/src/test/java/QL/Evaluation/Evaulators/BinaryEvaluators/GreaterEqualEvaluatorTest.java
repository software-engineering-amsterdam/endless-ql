package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class GreaterEqualEvaluatorTest {
    GreaterEqualEvaluator greaterEqualEvaluatorTest;
    @Property
    public void evaluateInt(int left, int right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);
        boolean ans = left >= right;

        greaterEqualEvaluatorTest = new GreaterEqualEvaluator(leftToTest, rightToTest);
        assertEquals(ans, greaterEqualEvaluatorTest.evaluate().getValue());
    }

    @Property
    public void evaluateDouble(double left, double right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);
        boolean ans = left >= right;

        greaterEqualEvaluatorTest = new GreaterEqualEvaluator(leftToTest, rightToTest);
        assertEquals(ans, greaterEqualEvaluatorTest.evaluate().getValue());
    }


}