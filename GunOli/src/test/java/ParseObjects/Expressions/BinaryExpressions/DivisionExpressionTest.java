package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import org.junit.Test;

import static ParseObjects.Expressions.EvaluationType.Decimal;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.junit.Assert.*;

public class DivisionExpressionTest {

    private DecimalConstant a = new DecimalConstant(10.00);
    private DecimalConstant b = new DecimalConstant(2.00);
    private DecimalConstant c = new DecimalConstant(-10.00);
    private DecimalConstant MAXDecimal = new DecimalConstant(Double.MAX_VALUE);
    private DecimalConstant MINDecimal = new DecimalConstant(Double.MIN_VALUE);
    private DivisionExpression posToTest = new DivisionExpression(a,b);
    private DivisionExpression negToTest = new DivisionExpression(c,b);
    private DivisionExpression maxToTest = new DivisionExpression(MINDecimal,MAXDecimal);


    @Test
    public void evaluate() {


        Double posTest = Double.parseDouble(posToTest.evaluate().getValue().toString());
        Double negTest = Double.parseDouble(negToTest.evaluate().getValue().toString());
        Double maxTest = Double.parseDouble(maxToTest.evaluate().getValue().toString());

        Double posEXP = 5.00;
        Double negEXP = -5.00;
        Double maxEXP = 0.0;

        assertEquals(maxEXP, maxTest);
        assertEquals(negEXP, negTest);
        assertEquals(posEXP, posTest);
    }

}