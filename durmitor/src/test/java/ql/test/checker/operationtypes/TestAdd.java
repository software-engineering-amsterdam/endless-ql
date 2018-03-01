package ql.test.checker.operationtypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ql.ast.expression.Add;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;

public class TestAdd {
    
    @Test
    public void testBoolAdd() {
        Literal<?> firstOperand = new BoolLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testStrAdd() {
        Literal<?> firstOperand = new StrLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Str.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testIntAdd() {
        Literal<?> firstOperand = new IntLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Int.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Decimal.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testDecimalAdd() {
        Literal<?> firstOperand = new DecimalLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Decimal.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Decimal.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testMoneyAdd() {
        Literal<?> firstOperand = new MoneyLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Money.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testDateAdd() {
        Literal<?> firstOperand = new DateLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
    
    @Test
    public void testUndefinedAdd() {
        Literal<?> firstOperand = new UndefinedLiteral();
        assertEquals(Undefined.class, new Add(firstOperand,new BoolLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new StrLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new IntLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DecimalLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new MoneyLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new DateLiteral()).getType().getClass());
        assertEquals(Undefined.class, new Add(firstOperand,new UndefinedLiteral()).getType().getClass());
    }
}
