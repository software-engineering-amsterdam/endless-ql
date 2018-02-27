package ql.value;

import ql.ast.type.Type;

public class Money extends Value<String> {

    private String value;
    
    public Money() { 
        this.value = "0.00";
    }
    
    public Money(String value) { 
        this.value = value;
    }

    public Money(double value) {
        this.value = String.format("%.2f", value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }
    
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Money();
    }

    // Money as unary operand
    
    @Override public Value<?> negative() { return new Money(Double.parseDouble(value) * -1); }
    @Override public Value<?> positive() { return this; }

    // Money as second operand
    
    @Override public Value<?> multiply(Numeric firstOperand) { return new Money(firstOperand.getValue().doubleValue() * Double.parseDouble(value)); }
    @Override public Value<?> addTo(Money firstOperand) { return new Money(Double.parseDouble(firstOperand.getValue()) + Double.parseDouble(value)); }
    @Override public Value<?> subtractFrom(Money firstOperand) { return new Money(Double.parseDouble(firstOperand.getValue()) - Double.parseDouble(value)); }
    @Override public Value<?> greaterThan(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) < Double.parseDouble(value)); }
    @Override public Value<?> greaterThanEqualTo(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) <= Double.parseDouble(value)); }
    @Override public Value<?> lessThan(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) > Double.parseDouble(value)); }
    @Override public Value<?> lessThanEqualTo(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) >= Double.parseDouble(value)); }
    @Override public Value<?> equalTo(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) == Double.parseDouble(value)); }
    @Override public Value<?> notEqualTo(Money firstOperand) { return new Bool(Double.parseDouble(firstOperand.getValue()) != Double.parseDouble(value)); }

    // Money as first operand

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
