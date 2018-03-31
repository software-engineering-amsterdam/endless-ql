package ql.types;

import ql.visitors.TypeVisitor;

public class Money extends Type {
    public Money(int lineNumber, java.lang.String name) {
        super(lineNumber, name);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
