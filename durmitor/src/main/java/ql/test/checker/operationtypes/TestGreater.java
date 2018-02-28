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

public class TestGreater {
    
    @Test
    public void testBoolGreater() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testStrGreater() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testIntGreater() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalGreater() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyGreater() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testDateGreater() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedGreater() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.greater(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.greater(new Undefined()).getClass());
    }
}
