package main.org.uva.ql.ast.type;

import main.org.uva.ql.visitor.TypeVisitor;

public class IntegerType extends Type {
    @Override
    public String toString() {
        return "IntegerType";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
