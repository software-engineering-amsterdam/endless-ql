package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class EqualExpressionTest {

    private BooleanConstant a = new BooleanConstant(true);
    private BooleanConstant b = new BooleanConstant(true);

    private IntegerConstant c = new IntegerConstant(1);
    private IntegerConstant d = new IntegerConstant(1);

    private DecimalConstant e = new DecimalConstant(2.00);
    private DecimalConstant f = new DecimalConstant(2.00);

    private DateConstant g = new DateConstant("02-02-1994");
    private DateConstant h = new DateConstant("02-02-1994");

    private MoneyConstant i = new MoneyConstant(2300.00);
    private MoneyConstant j = new MoneyConstant(2300);

    private MoneyConstant k = new MoneyConstant("2300");
    private MoneyConstant l = new MoneyConstant("2300");

    private StringConstant m = new StringConstant("Hallo");
    private StringConstant n = new StringConstant("Hallo");

    private EqualExpression bool = new EqualExpression(a, b);
    private EqualExpression integers = new EqualExpression(c, d);
    private EqualExpression dec = new EqualExpression(e, f);
    private EqualExpression date = new EqualExpression(g,h);
    private EqualExpression money = new EqualExpression(i, j);
    private EqualExpression moneyString = new EqualExpression(k,l);
    private EqualExpression strings = new EqualExpression(m,n);

    private Boolean boolTest = bool.evaluate().getValue();
    private Boolean intTest = integers.evaluate().getValue();
    private Boolean decTest = dec.evaluate().getValue();
    private Boolean dateTest = date.evaluate().getValue();
    private Boolean moneyTest = money.evaluate().getValue();
    private Boolean moneyStringTest = moneyString.evaluate().getValue();
    private Boolean stringTest = strings.evaluate().getValue();



    @Test
    public void evaluate() {

        boolean expBool = true;
        boolean expInt = true;
        boolean expDec = true;
        boolean expDate = true;
        boolean expMoney = true;
        boolean expMoneyString = true;
        boolean expString = true;

        assertEquals(expBool, boolTest);
        assertEquals(expInt, intTest);
        assertEquals(expDate, dateTest);
        assertEquals(expDec, decTest);
        assertEquals(expMoney, moneyTest);
        assertEquals(expMoneyString, moneyStringTest);
        assertEquals(expString, stringTest);
    }
}