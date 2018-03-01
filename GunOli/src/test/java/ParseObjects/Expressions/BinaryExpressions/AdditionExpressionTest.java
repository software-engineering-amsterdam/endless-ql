package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class AdditionExpressionTest {

    @Property
    public void evaluate(int autoA, int autoB) {
        IntegerConstant b = new IntegerConstant( autoA);
        IntegerConstant c = new IntegerConstant( autoB);

        AdditionExpression test  = new AdditionExpression(b,c);
        assertEquals((autoA + autoB), test.evaluate().getValue());
    }
}