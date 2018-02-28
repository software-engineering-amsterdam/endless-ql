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

public class TestLess {
    
    @Test
    public void testBoolLess() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testStrLess() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testIntLess() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalLess() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyLess() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testDateLess() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedLess() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.less(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.less(new Undefined()).getClass());
    }
}
