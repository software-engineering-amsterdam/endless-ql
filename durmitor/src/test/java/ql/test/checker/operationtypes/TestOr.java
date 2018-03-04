package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Or;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestOr {
    
    @Test
    public void testBoolOr() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(BoolLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrOr() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntOr() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalOr() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyOr() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateOr() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedOr() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Or(firstOperand, new UndefinedLiteral()).getClass());
    }
}
