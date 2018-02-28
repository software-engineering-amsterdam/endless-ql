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

public class TestAnd {
    
    @Test
    public void testBoolAnd() {
        Value<?> firstOperand = new Bool();
        assertEquals(Bool.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testStrAnd() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testIntAnd() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalAnd() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyAnd() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testDateAnd() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedAnd() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.and(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.and(new Undefined()).getClass());
    }
}
