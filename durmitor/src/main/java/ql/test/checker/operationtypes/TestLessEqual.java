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

public class TestLessEqual {
    
    @Test
    public void testBoolLessEqual() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testStrLessEqual() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testIntLessEqual() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalLessEqual() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyLessEqual() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testDateLessEqual() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Bool.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedLessEqual() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.lessEqual(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.lessEqual(new Undefined()).getClass());
    }
}
