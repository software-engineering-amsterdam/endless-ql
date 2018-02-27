package ql.value;

import ql.ast.type.Type;

public class Int extends Numeric {

    private int value;
    
    public Int() { 
        this.value = 0;
    }
    
    public Int(String value) { 
        this.value = Integer.parseInt(value);
    }

    public Int(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }
    
    @Override
    public void setValue(Number value) {
        this.value = value.intValue();
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Int();
    }

    // Int as unary operand
    
    @Override public Value<?> negative() { return new Int(value * -1); }
    @Override public Value<?> positive() { return this; }

    // Int as second operand
    
    @Override public Value<?> multiply(Int firstOperand) { return new Int(firstOperand.getValue() * value); }
    @Override public Value<?> multiply(Money firstOperand) { return new Money(Double.parseDouble(firstOperand.getValue()) * value); }
    @Override public Value<?> divide(Numeric firstOperand) { return new Decimal(firstOperand.getValue().doubleValue() / value); }
    @Override public Value<?> divide(Money firstOperand) { return new Money(Double.parseDouble(firstOperand.getValue()) * value); }
    @Override public Value<?> addTo(Int firstOperand) { return new Int(firstOperand.getValue() + value); }
    @Override public Value<?> addTo(Decimal firstOperand) { return new Decimal(firstOperand.getValue() + value); }
    @Override public Value<?> subtractFrom(Int firstOperand) { return new Int(firstOperand.getValue() - value); }
    @Override public Value<?> subtractFrom(Decimal firstOperand) { return new Decimal(firstOperand.getValue() - value); }
    @Override public Value<?> greaterThan(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() < value); }
    @Override public Value<?> greaterThanEqualTo(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() <= value); }
    @Override public Value<?> lessThan(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() > value); }
    @Override public Value<?> lessThanEqualTo(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() >= value); }
    @Override public Value<?> equalTo(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() == value); }
    @Override public Value<?> notEqualTo(Numeric firstOperand) { return new Bool(firstOperand.getValue().doubleValue() != value); }

    // Int as first operand
    
    @Override public Value<?> multiplyBy(Value<?> secondOperand) { return secondOperand.multiply(this); }
    @Override public Value<?> divideBy(Value<?> secondOperand) { return secondOperand.divide(this); }
    @Override public Value<?> add(Value<?> secondOperand) { return secondOperand.addTo(this); }
    @Override public Value<?> subtract(Value<?> secondOperand) { return secondOperand.subtractFrom(this); }
    @Override public Value<?> less(Value<?> secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Value<?> lessEqual(Value<?> secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Value<?> greater(Value<?> secondOperand) { return secondOperand.lessThan(this); }
    @Override public Value<?> greaterEqual(Value<?> secondOperand) { return secondOperand.lessThanEqualTo(this); }
    @Override public Value<?> equal(Value<?> secondOperand) { return secondOperand.equalTo(this); }
    @Override public Value<?> notEqual(Value<?> secondOperand) { return secondOperand.notEqualTo(this); }
}
