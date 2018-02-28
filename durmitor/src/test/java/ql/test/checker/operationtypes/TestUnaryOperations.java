package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;

public class TestUnaryOperations {
    
    @Test
    public void testNegation() {
        assertEquals(Bool.class, new Bool().negation().getClass());
        assertEquals(Undefined.class, new Str().negation().getClass());
        assertEquals(Undefined.class, new Int().negation().getClass());
        assertEquals(Undefined.class, new Decimal().negation().getClass());
        assertEquals(Undefined.class, new Money().negation().getClass());
        assertEquals(Undefined.class, new Date().negation().getClass());
        assertEquals(Undefined.class, new Undefined().negation().getClass());
    }
    
    @Test
    public void testPositive() {
        assertEquals(Undefined.class, new Bool().positive().getClass());
        assertEquals(Undefined.class, new Str().positive().getClass());
        assertEquals(Int.class, new Int().positive().getClass());
        assertEquals(Decimal.class, new Decimal().positive().getClass());
        assertEquals(Money.class, new Money().positive().getClass());
        assertEquals(Undefined.class, new Date().positive().getClass());
        assertEquals(Undefined.class, new Undefined().positive().getClass());
    }
    
    @Test
    public void testNegative() {
        assertEquals(Undefined.class, new Bool().negative().getClass());
        assertEquals(Undefined.class, new Str().negative().getClass());
        assertEquals(Int.class, new Int().negative().getClass());
        assertEquals(Decimal.class, new Decimal().negative().getClass());
        assertEquals(Money.class, new Money().negative().getClass());
        assertEquals(Undefined.class, new Date().negative().getClass());
        assertEquals(Undefined.class, new Undefined().negative().getClass());
    }
}
