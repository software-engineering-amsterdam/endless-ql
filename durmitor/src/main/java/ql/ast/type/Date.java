package ql.ast.type;

import ql.value.Value;
import ql.visitors.interfaces.TypeVisitor;

public class Date extends Type {

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDate();
    }

    @Override
    public boolean isDate() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.value.Date();
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
}
