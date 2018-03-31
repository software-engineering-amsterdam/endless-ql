package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Value;
import QL.Evaluation.Values.NumericValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import QL.Evaluation.Value;

import sun.awt.SunHints;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class AdditionEvaluatorTest {

    private AdditionEvaluator additionEvaluatorTest;

    @Property
    public void evaluateInt(int left, int right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);
        double ans = left * 1.0 + right * 1.0;
        additionEvaluatorTest = new AdditionEvaluator(leftToTest, rightToTest);
        assertEquals( ans, additionEvaluatorTest.evaluate().getValue());
    }

    @Property
    public void evaluateDouble(double left, double right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);
        double ans = left + right;
        additionEvaluatorTest = new AdditionEvaluator(leftToTest, rightToTest);
        assertEquals( ans, additionEvaluatorTest.evaluate().getValue() );
    }
}