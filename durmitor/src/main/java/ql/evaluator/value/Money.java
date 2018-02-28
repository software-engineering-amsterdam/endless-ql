package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.MoneyAdd;
import ql.evaluator.arithmetic.divide.MoneyDivide;
import ql.evaluator.arithmetic.multiply.MoneyMultiply;
import ql.evaluator.arithmetic.subtract.MoneySubtract;
import ql.evaluator.comparisons.equal.MoneyEqual;
import ql.evaluator.comparisons.greater.MoneyGreater;
import ql.evaluator.comparisons.greaterequal.MoneyGreaterEqual;
import ql.evaluator.comparisons.less.MoneyLess;
import ql.evaluator.comparisons.lessequal.MoneyLessEqual;
import ql.evaluator.comparisons.notequal.MoneyNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Money extends Numeric {

    private Double value;

    public Money() {
        this.value = 0.0;
    }

    public Money(String value) {
        this.value = Double.valueOf(value);
    }

    public Money(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Money();
    }

    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> negative() {
        return new Money(value * -1);
    }

    @Override
    public Value<?> positive() {
        return this;
    }

    @Override
    public Value<?> add(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyAdd(this));
    }

    @Override
    public Value<?> subtract(Value<?> secondOperand) {
        return secondOperand.accept(new MoneySubtract(this));
    }

    @Override
    public Value<?> multiply(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyMultiply(this));
    }

    @Override
    public Value<?> divide(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyDivide(this));
    }

    @Override
    public Value<?> less(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyLess(this));
    }

    @Override
    public Value<?> lessEqual(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyLessEqual(this));
    }

    @Override
    public Value<?> greater(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyGreater(this));
    }

    @Override
    public Value<?> greaterEqual(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyGreaterEqual(this));
    }

    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyEqual(this));
    }

    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new MoneyNotEqual(this));
    }
}
