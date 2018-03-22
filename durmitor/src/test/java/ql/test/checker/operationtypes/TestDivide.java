package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Divide;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;

public class TestDivide {
    
    @Test
    public void testBoolDivide() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testStrDivide() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testIntDivide() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(DecimalLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(DecimalLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testDecimalDivide() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(DecimalLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(DecimalLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testMoneyDivide() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(MoneyLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(MoneyLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testDateDivide() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
    
    @Test
    public void testUndefinedDivide() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new BoolLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new StrLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new IntLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DecimalLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new MoneyLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new DateLiteral()).evaluate().getClass());
        assertEquals(UndefinedLiteral.class, new Divide(firstOperand, new UndefinedLiteral()).evaluate().getClass());
    }
}
