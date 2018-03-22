package org.uva.ql.ast.type;

import org.uva.ql.visitor.TypeVisitor;

public class IntegerType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return "IntegerType".equals(type.toString());
    }

    @Override
    public String toString() {
        return "IntegerType";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
