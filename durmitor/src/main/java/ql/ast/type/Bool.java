package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToBool;
import ql.visitors.checker.operationtypes.BooleanAndOr;
import ql.visitors.checker.operationtypes.TypeComparison;
import ql.visitors.interfaces.TypeVisitor;

public class Bool extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "boolean";
    }

    @Override
    public boolean equals(Type t) {
        return t.isBoolean();
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Bool();
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToBool());
    }
    
    @Override
    public Type negation() {
        return this;
    }
    
    @Override
    public Type and(Type secondOperand) {
        return secondOperand.accept(new BooleanAndOr());
    }
    
    @Override
    public Type or(Type secondOperand) {
        return secondOperand.accept(new BooleanAndOr());
    }
    
    @Override
    public Type equal(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }
    
    @Override
    public Type notEqual(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }
}
