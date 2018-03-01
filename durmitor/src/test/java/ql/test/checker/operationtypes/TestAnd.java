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
        assertEquals(BoolLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrAnd() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntAnd() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalAnd() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyAnd() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateAnd() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedAnd() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new And(firstOperand, new UndefinedLiteral()).getClass());
    }
}
