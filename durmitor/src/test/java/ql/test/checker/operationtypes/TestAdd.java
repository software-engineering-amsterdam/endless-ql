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

public class TestAdd {
    
    @Test
    public void testBoolAdd() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testStrAdd() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Str.class, firstOperand.add(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testIntAdd() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Int.class, firstOperand.add(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalAdd() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Decimal.class, firstOperand.add(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyAdd() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Money.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testDateAdd() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedAdd() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.add(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.add(new Undefined()).getClass());
    }
}
