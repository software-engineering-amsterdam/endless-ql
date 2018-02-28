package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToDecimal;
import ql.visitors.interfaces.TypeVisitor;

public class Decimal extends Numeric {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "decimal";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDecimal();
    }

    @Override
    public boolean isDecimal() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Decimal();
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToDecimal());
    }
}
