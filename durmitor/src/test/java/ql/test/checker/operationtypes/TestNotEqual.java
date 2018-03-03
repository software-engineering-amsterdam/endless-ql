package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.NotEqual;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestNotEqual {
    
    @Test
    public void testBoolNotEqual() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrNotEqual() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntNotEqual() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalNotEqual() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyNotEqual() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateNotEqual() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedNotEqual() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new NotEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
}
