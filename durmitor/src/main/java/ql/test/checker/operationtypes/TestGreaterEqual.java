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

public class TestGreaterEqual {
    
    @Test
    public void testBoolGreaterEqual() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testStrGreaterEqual() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testIntGreaterEqual() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalGreaterEqual() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyGreaterEqual() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDateGreaterEqual() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedGreaterEqual() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greaterEqual(new Undefined()).getClass());
    }
}
