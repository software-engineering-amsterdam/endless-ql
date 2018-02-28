package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToStr;
import ql.visitors.checker.operationtypes.StrAdd;
import ql.visitors.checker.operationtypes.TypeComparison;
import ql.visitors.interfaces.TypeVisitor;

public class Str extends Type {

    private ql.evaluator.value.Str value;

    public Value<String> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "string";
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Str();
    }

    @Override
    public boolean equals(Type t) {
        return t.isString();
    }

    @Override
    public boolean isString() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToStr());
    }

    @Override
    public Type add(Type secondOperand) {
        return secondOperand.accept(new StrAdd());
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
