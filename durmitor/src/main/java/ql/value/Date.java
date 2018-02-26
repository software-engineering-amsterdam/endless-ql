package ql.value;

import java.time.LocalDate;

import ql.ast.type.Type;

public class Date extends Value<LocalDate> {

    private LocalDate value;
    
    public Date() { 
        this.value = null;
    }
    
    public Date(String value) {
        this.value = LocalDate.parse(value);
    }

    public Date(LocalDate localDate) {
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public LocalDate getValue() {
        return value;
    }
    
    @Override
    public void setValue(LocalDate value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
    }

    // Date as second operand
    
    @Override public Value<?> greaterThan(Date firstOperand) { return new Bool(firstOperand.getValue().isBefore(value)); }
    @Override public Value<?> greaterThanEqualTo(Date firstOperand) { return new Bool(firstOperand.getValue().isEqual(value) || firstOperand.getValue().isBefore(value)); }
    @Override public Value<?> lessThan(Date firstOperand) { return new Bool(firstOperand.getValue().isAfter(value)); }
    @Override public Value<?> lessThanEqualTo(Date firstOperand) { return new Bool(firstOperand.getValue().isEqual(value) || firstOperand.getValue().isAfter(value)); }
    @Override public Value<?> equalTo(Date firstOperand) { return new Bool(firstOperand.getValue().isEqual(value)); }
    @Override public Value<?> notEqualTo(Date firstOperand) { return new Bool(!firstOperand.getValue().isEqual(value)); }
    
    // Date as first operand
    
    @Override public Value<?> less(Value<?> secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Value<?> lessEqual(Value<?> secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Value<?> greater(Value<?> secondOperand) { return secondOperand.lessThan(this); }
    @Override public Value<?> greaterEqual(Value<?> secondOperand) { return secondOperand.lessThanEqualTo(this); }
    @Override public Value<?> equal(Value<?> secondOperand) { return secondOperand.equalTo(this); }
    @Override public Value<?> notEqual(Value<?> secondOperand) { return secondOperand.notEqualTo(this); }
}
