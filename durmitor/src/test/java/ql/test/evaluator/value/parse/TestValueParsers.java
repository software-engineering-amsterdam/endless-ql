package ql.test.evaluator.value.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.type.Type;
import ql.evaluator.value.Bool;
import ql.evaluator.value.Date;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Int;
import ql.evaluator.value.Money;
import ql.evaluator.value.Str;
import ql.evaluator.value.Undefined;

public class TestValueParsers {
    
    @Test
    public void testValueToBool() {
        Type target = new ql.ast.type.Bool();
        assertEquals(Bool.class, target.parse(new Bool()).getClass());
        assertEquals(Bool.class, target.parse(new Str()).getClass());
        assertEquals(Bool.class, target.parse(new Int()).getClass());
        assertEquals(Bool.class, target.parse(new Decimal()).getClass());
        assertEquals(Bool.class, target.parse(new Money()).getClass());
        assertEquals(Undefined.class, target.parse(new Date()).getClass());
        assertEquals(Bool.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToStr() {
        Type target = new ql.ast.type.Str();
        assertEquals(Str.class, target.parse(new Bool()).getClass());
        assertEquals(Str.class, target.parse(new Str()).getClass());
        assertEquals(Str.class, target.parse(new Int()).getClass());
        assertEquals(Str.class, target.parse(new Decimal()).getClass());
        assertEquals(Str.class, target.parse(new Money()).getClass());
        assertEquals(Str.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToInt() {
        Type target = new ql.ast.type.Int();
        assertEquals(Undefined.class, target.parse(new Bool()).getClass());
        assertEquals(Undefined.class, target.parse(new Str()).getClass());
        assertEquals(Int.class, target.parse(new Int()).getClass());
        assertEquals(Int.class, target.parse(new Decimal()).getClass());
        assertEquals(Int.class, target.parse(new Money()).getClass());
        assertEquals(Undefined.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToDecimal() {
        Type target = new ql.ast.type.Decimal();
        assertEquals(Undefined.class, target.parse(new Bool()).getClass());
        assertEquals(Undefined.class, target.parse(new Str()).getClass());
        assertEquals(Decimal.class, target.parse(new Int()).getClass());
        assertEquals(Decimal.class, target.parse(new Decimal()).getClass());
        assertEquals(Decimal.class, target.parse(new Money()).getClass());
        assertEquals(Undefined.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToMoney() {
        Type target = new ql.ast.type.Money();
        assertEquals(Undefined.class, target.parse(new Bool()).getClass());
        assertEquals(Undefined.class, target.parse(new Str()).getClass());
        assertEquals(Money.class, target.parse(new Int()).getClass());
        assertEquals(Money.class, target.parse(new Decimal()).getClass());
        assertEquals(Money.class, target.parse(new Money()).getClass());
        assertEquals(Undefined.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToDate() {
        Type target = new ql.ast.type.Date();
        assertEquals(Undefined.class, target.parse(new Bool()).getClass());
        assertEquals(Undefined.class, target.parse(new Str()).getClass());
        assertEquals(Undefined.class, target.parse(new Int()).getClass());
        assertEquals(Undefined.class, target.parse(new Decimal()).getClass());
        assertEquals(Undefined.class, target.parse(new Money()).getClass());
        assertEquals(Date.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
    
    @Test
    public void testValueToUndefined() {
        Type target = new ql.ast.type.Undefined();
        assertEquals(Undefined.class, target.parse(new Bool()).getClass());
        assertEquals(Undefined.class, target.parse(new Str()).getClass());
        assertEquals(Undefined.class, target.parse(new Int()).getClass());
        assertEquals(Undefined.class, target.parse(new Decimal()).getClass());
        assertEquals(Undefined.class, target.parse(new Money()).getClass());
        assertEquals(Undefined.class, target.parse(new Date()).getClass());
        assertEquals(Undefined.class, target.parse(new Undefined()).getClass());
    }
}
