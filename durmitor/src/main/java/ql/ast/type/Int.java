package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToInt;
import ql.visitors.interfaces.TypeVisitor;

public class Int extends Numeric {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "integer";
    }

    @Override
    public boolean equals(Type t) {
        return t.isInteger();
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Int();
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToInt());
    }
}
