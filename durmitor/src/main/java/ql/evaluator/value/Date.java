package ql.evaluator.value;

import java.time.LocalDate;

import ql.ast.type.Type;
import ql.evaluator.comparisons.equal.DateEqual;
import ql.evaluator.comparisons.greater.DateGreater;
import ql.evaluator.comparisons.greaterequal.DateGreaterEqual;
import ql.evaluator.comparisons.less.DateLess;
import ql.evaluator.comparisons.lessequal.DateLessEqual;
import ql.evaluator.comparisons.notequal.DateNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Date extends Value<LocalDate> {

    private LocalDate value;

    public Date() {
        this.value = LocalDate.MIN;
    }

    public Date(String value) {
        this.value = LocalDate.parse(value);
    }

    public Date(LocalDate value) {
        this.value = value;
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
    public Type getType() {
        return new ql.ast.type.Date();
    }

    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> less(Value<?> secondOperand) {
        return secondOperand.accept(new DateLess(this));
    }

    @Override
    public Value<?> lessEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DateLessEqual(this));
    }

    @Override
    public Value<?> greater(Value<?> secondOperand) {
        return secondOperand.accept(new DateGreater(this));
    }

    @Override
    public Value<?> greaterEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DateGreaterEqual(this));
    }

    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new DateEqual(this));
    }

    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DateNotEqual(this));
    }
}
