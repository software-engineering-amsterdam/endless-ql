package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.*;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class EqualExpressionTest {

    private EqualExpression expressionTest;

    @Property
    public void evaluate(int left, int right) {
        IntegerConstant leftToTest = new IntegerConstant(left,0);
        IntegerConstant rightToTest = new IntegerConstant(right,0);

        expressionTest = new EqualExpression(leftToTest, rightToTest,0);

        assertEquals((left == right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluate(boolean left, boolean right) {

        BooleanConstant leftToTest = new BooleanConstant(left,0);
        BooleanConstant rightToTest = new BooleanConstant(right,0);

        expressionTest = new EqualExpression(leftToTest,rightToTest,0);

        assertEquals((left == right), expressionTest.evaluate().getValue());
    }

    @Property
    public void evaluate(double left, double right) {
        DecimalConstant leftToTest = new DecimalConstant(left,0);
        DecimalConstant rightToTest = new DecimalConstant(right,0);

        expressionTest = new EqualExpression(leftToTest, rightToTest,0);

        assertEquals((left == right), expressionTest.evaluate().getValue());

    }
    @Property
    public void evaluate(@InRange(min = "01", max = "31") int day1, @InRange(min = "01", max = "12")int month1, @InRange(min = "1000", max = "3000")int year1, @InRange(min = "01", max = "31")int day2,  @InRange(min = "01", max = "31") int month2, @InRange(min = "1000", max = "3000")int year2 ) {
        String firstYear = day1  + "-" + month1 + "-" + year1;
        String secondYear = day2 + "-" + month2 + "-" + year2;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate firstDate = LocalDate.parse(firstYear, dateTimeFormatter);
        LocalDate secondDate = LocalDate.parse(secondYear, dateTimeFormatter);

        DateConstant left = new DateConstant(firstDate,0);
        DateConstant right = new DateConstant(secondDate,0);

        expressionTest = new EqualExpression(left, right,0);

        assertEquals(firstYear.equals(secondYear), expressionTest.evaluate().getValue());

    }

    @Property
    public void evaluate(String left, String right) {

        StringConstant leftToTest = new StringConstant(left,0);
        StringConstant rightToTest = new StringConstant(right,0);

        expressionTest = new EqualExpression(leftToTest, rightToTest,0);

        assertEquals((left.equals(right)), expressionTest.evaluate().getValue());
    }
}