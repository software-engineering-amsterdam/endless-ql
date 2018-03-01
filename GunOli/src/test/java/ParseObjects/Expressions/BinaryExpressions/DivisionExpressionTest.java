package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class DivisionExpressionTest {

    private DivisionExpression expressionTest;
    @Property
    public void evaluateINT(int autoA, int autoB) {
        IntegerConstant b = new IntegerConstant( autoA);
        IntegerConstant c = new IntegerConstant( autoB);

        expressionTest  = new DivisionExpression(b,c);
        assertEquals((autoA / autoB), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluateDOUBLE(double autoA, double autoB) {
        DecimalConstant b = new DecimalConstant( autoA);
        DecimalConstant c = new DecimalConstant( autoB);

        expressionTest  = new DivisionExpression(b,c);
        assertEquals((autoA / autoB), expressionTest.evaluate().getValue());

    }

}