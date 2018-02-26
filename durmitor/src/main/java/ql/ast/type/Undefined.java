package ql.ast.type;

import ql.value.Value;
import ql.visitors.interfaces.TypeVisitor;

public class Undefined extends Type {

    @Override
    public Value<?> toValue() {
        return new ql.value.Undefined();
    }

    @Override
    public String toString() {
        return "undefined";
    }

    @Override
    public boolean equals(Type t) {
        return t.isUndefined();
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
}
