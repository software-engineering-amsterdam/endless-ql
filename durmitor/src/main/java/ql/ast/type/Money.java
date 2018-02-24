package ql.ast.type;

import ql.value.Value;
import ql.visitors.interfaces.TypeVisitor;

public class Money extends Type {

    @Override
    public String toString() {
        return "money";
    }

    @Override
    public boolean equals(Type t) {
        return t.isMoney();
    }

    @Override
    public boolean isMoney() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.value.Money();
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
}
