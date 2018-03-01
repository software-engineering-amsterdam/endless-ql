package ql.test.evaluator.value.parse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Type;

public class TestValueParsers {
    
    @Test
    public void testValueToBool() {
        Type target = new ql.ast.type.Bool();
        assertEquals(BoolLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(BoolLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(BoolLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(BoolLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(BoolLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(BoolLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToStr() {
        Type target = new ql.ast.type.Str();
        assertEquals(StrLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(StrLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(StrLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(StrLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(StrLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(StrLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToInt() {
        Type target = new ql.ast.type.Int();
        assertEquals(UndefinedLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(IntLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(IntLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(IntLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToDecimal() {
        Type target = new ql.ast.type.Decimal();
        assertEquals(UndefinedLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(DecimalLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(DecimalLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(DecimalLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToMoney() {
        Type target = new ql.ast.type.Money();
        assertEquals(UndefinedLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(MoneyLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(MoneyLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(MoneyLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToDate() {
        Type target = new ql.ast.type.Date();
        assertEquals(UndefinedLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(DateLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
    
    @Test
    public void testValueToUndefined() {
        Type target = new ql.ast.type.Undefined();
        assertEquals(UndefinedLiteral.class, target.parse(new BoolLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new StrLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new IntLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DecimalLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new MoneyLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new DateLiteral()).getClass());
        assertEquals(UndefinedLiteral.class, target.parse(new UndefinedLiteral()).getClass());
    }
}
