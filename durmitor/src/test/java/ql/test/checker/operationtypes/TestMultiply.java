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

public class TestMultiply {
    
    @Test
    public void testBoolMultiply() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testStrMultiply() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testIntMultiply() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Int.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Money.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalMultiply() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Decimal.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Money.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyMultiply() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Money.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Money.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testDateMultiply() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedMultiply() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.multiply(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.multiply(new Undefined()).getClass());
    }
}
