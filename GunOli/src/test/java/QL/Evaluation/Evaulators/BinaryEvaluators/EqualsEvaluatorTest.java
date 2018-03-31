package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.NumericValue;
import QL.Evaluation.Values.StringValue;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class EqualsEvaluatorTest {

    EqualsEvaluator equalsEvaluatorTest;

    @Property
    public void evaluateInt(int left, int right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        equalsEvaluatorTest = new EqualsEvaluator(leftToTest, rightToTest);
        boolean ans = left == right;

        assertEquals(ans, equalsEvaluatorTest.evaluate().getValue());
    }

    @Property
    public void evaluateDouble(double left, double right){

        NumericValue leftToTest = new NumericValue(left);
        NumericValue rightToTest = new NumericValue(right);

        equalsEvaluatorTest = new EqualsEvaluator(leftToTest, rightToTest);
        boolean ans = left == right;

        assertEquals(ans, equalsEvaluatorTest.evaluate().getValue());
    }

    @Property
    public void evaluateBoolean(boolean left, boolean right){
        //System.out.println(left);
        //System.out.println(right);
        BooleanValue leftToTest = new BooleanValue(left);
        BooleanValue rightToTest = new BooleanValue(right);

        equalsEvaluatorTest = new EqualsEvaluator(leftToTest, rightToTest);
        boolean ans = left && right;
        //System.out.println("ans: " + ans);

        //System.out.println("Test: " + equalsEvaluatorTest.evaluate().getValue());
         assertEquals(ans, equalsEvaluatorTest.evaluate().getValue());
    }

    @Property
    public void evaluateString(String left, String right){
        //System.out.println(left);
        //System.out.println(right);
        StringValue leftToTest = new StringValue(left);
        StringValue rightToTest = new StringValue(right);

        equalsEvaluatorTest = new EqualsEvaluator(leftToTest, rightToTest);
        boolean ans = left == right;
        //System.out.println("ans: " + ans);

        //System.out.println("Test: " + equalsEvaluatorTest.evaluate().getValue());
        assertEquals(ans, equalsEvaluatorTest.evaluate().getValue());
    }

}