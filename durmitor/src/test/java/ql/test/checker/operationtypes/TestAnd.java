package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.And;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestAnd {
    
    @Test
    public void testBoolAnd() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(BoolLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testStrAnd() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testIntAnd() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testDecimalAnd() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testMoneyAnd() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testDateAnd() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testUndefinedAnd() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
}
