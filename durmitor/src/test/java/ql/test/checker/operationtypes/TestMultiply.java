package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestMultiply {
    
    @Test
    public void testBoolMultiply() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrMultiply() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntMultiply() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(IntLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(DecimalLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(MoneyLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalMultiply() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(DecimalLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(DecimalLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(MoneyLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyMultiply() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(MoneyLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(MoneyLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateMultiply() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedMultiply() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, firstOperand.multiply(new UndefinedLiteral()).getClass());
    }
}
