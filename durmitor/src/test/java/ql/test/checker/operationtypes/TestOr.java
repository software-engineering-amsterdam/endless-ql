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

public class TestOr {
    
    @Test
    public void testBoolOr() {
        Value<?> firstOperand = new Bool();
        assertEquals(Bool.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testStrOr() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testIntOr() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalOr() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyOr() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testDateOr() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedOr() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.or(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.or(new Undefined()).getClass());
    }
}
