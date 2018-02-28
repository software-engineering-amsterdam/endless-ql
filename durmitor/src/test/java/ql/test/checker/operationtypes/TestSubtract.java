package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.evaluator.value.Bool;
import ql.evaluator.value.Date;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Int;
import ql.evaluator.value.Money;
import ql.evaluator.value.Str;
import ql.evaluator.value.Undefined;
import ql.evaluator.value.Value;

public class TestSubtract {
    
    @Test
    public void testBoolSubtract() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testStrSubtract() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testIntSubtract() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Int.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalSubtract() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Decimal.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneySubtract() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Money.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testDateSubtract() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedSubtract() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.subtract(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.subtract(new Undefined()).getClass());
    }
}
