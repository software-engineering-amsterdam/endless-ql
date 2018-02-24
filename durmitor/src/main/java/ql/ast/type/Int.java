package ql.ast.type;

import ql.value.Value;
import ql.visitors.interfaces.TypeVisitor;

public class Int extends Numeric {

    @Override
    public String toString() {
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
        return new ql.value.Int();
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
}
