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

public class TestDivide {
    
    @Test
    public void testBoolDivide() {
        Value<?> firstOperand = new Bool();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testStrDivide() {
        Value<?> firstOperand = new Str();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testIntDivide() {
        Value<?> firstOperand = new Int();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Decimal.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testDecimalDivide() {
        Value<?> firstOperand = new Decimal();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Decimal.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Decimal.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testMoneyDivide() {
        Value<?> firstOperand = new Money();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Money.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Money.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testDateDivide() {
        Value<?> firstOperand = new Date();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
    
    @Test
    public void testUndefinedDivide() {
        Value<?> firstOperand = new Undefined();
        assertEquals(Undefined.class, firstOperand.divide(new Bool()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Str()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Int()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Decimal()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Money()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Date()).getClass());
        assertEquals(Undefined.class, firstOperand.divide(new Undefined()).getClass());
    }
}
