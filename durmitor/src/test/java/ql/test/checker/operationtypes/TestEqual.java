package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Equal;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestEqual {
    
    @Test
    public void testBoolEqual() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrEqual() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntEqual() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalEqual() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyEqual() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateEqual() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedEqual() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Equal(firstOperand, new UndefinedLiteral()).getClass());
    }
}
