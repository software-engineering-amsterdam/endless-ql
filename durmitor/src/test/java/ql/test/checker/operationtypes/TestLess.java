package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Less;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestLess {
    
    @Test
    public void testBoolLess() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrLess() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntLess() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalLess() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyLess() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateLess() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedLess() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new Less(firstOperand, new UndefinedLiteral()).getClass());
    }
}
