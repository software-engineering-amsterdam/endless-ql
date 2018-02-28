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

public class TestNotEqual {
    
    @Test
    public void testBoolNotEqual() {
        Value<?> firstOperand = new Bool();
        assertEquals(Bool.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testStrNotEqual() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testIntNotEqual() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalNotEqual() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyNotEqual() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDateNotEqual() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedNotEqual() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.notEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.notEqual(new Undefined()).getClass());
    }
}
