package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.*;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class EqualExpressionTest {

    private EqualExpression expressionTest;

    @Property
    public void evaluateINT(int left, int right) {
        IntegerConstant leftToTest = new IntegerConstant(left);
        IntegerConstant rightToTest = new IntegerConstant(right);

        expressionTest = new EqualExpression(leftToTest, rightToTest);

        assertEquals((left == right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluateBOOL(boolean left, boolean right) {

        BooleanConstant leftToTest = new BooleanConstant(left);
        BooleanConstant rightToTest = new BooleanConstant(right);

        expressionTest = new EqualExpression(leftToTest,rightToTest);

        assertEquals((left == right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluateDOUBLE(double left, double right) {
        DecimalConstant leftToTest = new DecimalConstant(left);
        DecimalConstant rightToTest = new DecimalConstant(right);

        expressionTest = new EqualExpression(leftToTest, rightToTest);

        assertEquals((left == right), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluateDATE(@InRange(min = "01", max = "31") int day1, @InRange(min = "01", max = "12")int month1, @InRange(min = "1000", max = "3000")int year1, @InRange(min = "01", max = "31")int day2,  @InRange(min = "01", max = "31") int month2, @InRange(min = "1000", max = "3000")int year2 ) {
        String firstYear = day1  + "-" + month1 + "-" + year1;
        String secondYear = day2 + "-" + month2 + "-" + year2;

        DateConstant left = new DateConstant(firstYear);
        DateConstant right = new DateConstant(secondYear);

        expressionTest = new EqualExpression(left, right);

        assertEquals(firstYear.equals(secondYear), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluateMoneyINT(int left, int right) {
        MoneyConstant leftToTest = new MoneyConstant(left);
        MoneyConstant rightToTest = new MoneyConstant(right);

        expressionTest = new EqualExpression(leftToTest, rightToTest);

        assertEquals((left == right), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluateMoneyDOUBLE(double left, double right) {
        MoneyConstant leftToTest = new MoneyConstant(left);
        MoneyConstant rightToTest = new MoneyConstant(right);

        expressionTest = new EqualExpression(leftToTest, rightToTest);

        assertEquals((left == right), expressionTest.evaluate().getValue());

    }

    @Property
    public void evaluateSTRING(String left, String right) {

        StringConstant leftToTest = new StringConstant(left);
        StringConstant rightToTest = new StringConstant(right);

        expressionTest = new EqualExpression(leftToTest, rightToTest);

        assertEquals((left.equals(right)), expressionTest.evaluate().getValue());
    }
}