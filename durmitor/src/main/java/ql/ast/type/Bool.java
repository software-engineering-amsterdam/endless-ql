package ql.ast.type;

import ql.value.Value;
import ql.visitors.interfaces.TypeVisitor;

public class Bool extends Type {

    @Override
    public String toString() {
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
        return new ql.value.Bool();
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
}
