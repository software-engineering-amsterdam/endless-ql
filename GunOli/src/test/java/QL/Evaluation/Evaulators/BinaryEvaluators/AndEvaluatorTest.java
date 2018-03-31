package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.BooleanValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class AndEvaluatorTest  {
    private AndEvaluator AndEvaluatorTest;
    @Property
    public void evaluateBool(Boolean left, Boolean right){

        BooleanValue leftToTest = new BooleanValue(left);
        BooleanValue rightToTest = new BooleanValue(right);

        AndEvaluatorTest = new AndEvaluator(leftToTest, rightToTest);
        assertEquals((left && right), AndEvaluatorTest.evaluate().getValue());
    }
}