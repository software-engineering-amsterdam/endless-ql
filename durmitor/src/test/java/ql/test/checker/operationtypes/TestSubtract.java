package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Subtract;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestSubtract {
    
    @Test
    public void testBoolSubtract() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrSubtract() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntSubtract() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(IntLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(DecimalLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalSubtract() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(DecimalLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(DecimalLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneySubtract() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(MoneyLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateSubtract() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedSubtract() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Subtract(firstOperand, new UndefinedLiteral()).getClass());
    }
}
