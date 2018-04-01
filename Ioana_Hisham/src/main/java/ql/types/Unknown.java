package ql.types;

import ql.visitors.TypeVisitor;

public class Unknown extends Type {
    public Unknown() {
        super(0, "Unknown");
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
