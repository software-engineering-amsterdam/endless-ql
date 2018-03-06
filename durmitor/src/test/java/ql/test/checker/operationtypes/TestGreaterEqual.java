package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.GreaterEqual;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestGreaterEqual {
    
    @Test
    public void testBoolGreaterEqual() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrGreaterEqual() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntGreaterEqual() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalGreaterEqual() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyGreaterEqual() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateGreaterEqual() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedGreaterEqual() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new GreaterEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
}
