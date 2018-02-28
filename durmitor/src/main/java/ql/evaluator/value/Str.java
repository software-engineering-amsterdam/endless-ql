package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.StrAdd;
import ql.evaluator.comparisons.equal.StrEqual;
import ql.evaluator.comparisons.notequal.StrNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Str extends Value<String> {

    private String value;

    public Str() {
        this.value = "";
    }

    public Str(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Str();
    }
    
    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> add(Value<?> secondOperand) {
        return secondOperand.accept(new StrAdd(this));
    }
    
    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new StrEqual(this));
    }
    
    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new StrNotEqual(this));
    }
}
