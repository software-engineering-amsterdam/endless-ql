package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Greater;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestGreater {
    
    @Test
    public void testBoolGreater() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrGreater() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntGreater() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalGreater() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyGreater() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateGreater() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedGreater() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Greater(firstOperand, new UndefinedLiteral()).getClass());
    }
}
