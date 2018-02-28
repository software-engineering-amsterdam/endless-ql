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

public class TestEqual {
    
    @Test
    public void testBoolEqual() {
        Value<?> firstOperand = new Bool();
        assertEquals(Bool.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testStrEqual() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testIntEqual() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalEqual() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyEqual() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testDateEqual() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedEqual() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.equal(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.equal(new Undefined()).getClass());
    }
}
