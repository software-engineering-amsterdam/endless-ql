package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.booleans.BoolAnd;
import ql.evaluator.booleans.BoolOr;
import ql.evaluator.comparisons.equal.BoolEqual;
import ql.evaluator.comparisons.notequal.BoolNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Bool extends Value<Boolean> {

    private boolean value;

    public Bool() {
        this.value = false;
    }

    public Bool(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public Bool(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Bool();
    }

    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> negation() {
        return new Bool(!value);
    }
    
    @Override
    public Value<?> and(Value<?> secondOperand) {
        return secondOperand.accept(new BoolAnd(this));
    }
    
    @Override
    public Value<?> or(Value<?> secondOperand) {
        return secondOperand.accept(new BoolOr(this));
    }

    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new BoolEqual(this));
    }

    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new BoolNotEqual(this));
    }
}
