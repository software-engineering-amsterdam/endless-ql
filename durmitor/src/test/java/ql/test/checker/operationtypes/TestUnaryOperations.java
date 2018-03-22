package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.Positive;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Bool;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Undefined;

public class TestUnaryOperations {
    
    @Test
    public void testNegation() {
        assertEquals(Bool.class, new Negation(new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negation(new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testPositive() {
        assertEquals(Undefined.class, new Positive(new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Positive(new StrLiteral()).getType().getClass());
        assertEquals(Int.class, new Positive(new IntLiteral()).getType().getClass());
        assertEquals(Decimal.class, new Positive(new DecimalLiteral()).getType().getClass());
        assertEquals(Money.class, new Positive(new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Positive(new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Positive(new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testNegative() {
        assertEquals(Undefined.class, new Negative(new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negative(new StrLiteral()).getType().getClass());
        assertEquals(Int.class, new Negative(new IntLiteral()).getType().getClass());
        assertEquals(Decimal.class, new Negative(new DecimalLiteral()).getType().getClass());
        assertEquals(Money.class, new Negative(new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negative(new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Negative(new UndefinedLiteral()).getType().getClass());
    }
}
