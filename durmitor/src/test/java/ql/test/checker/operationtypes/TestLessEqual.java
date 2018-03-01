package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.LessEqual;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestLessEqual {
    
    @Test
    public void testBoolLessEqual() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testStrLessEqual() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testIntLessEqual() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDecimalLessEqual() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testMoneyLessEqual() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testDateLessEqual() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(BoolLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testUndefinedLessEqual() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, new LessEqual(firstOperand, new UndefinedLiteral()).getClass());
    }
}
