package ql.visitors.interfaces;

import ql.ast.expression.literal.Literal;

public interface ValueVisitable {
    
    public Literal<?> accept(ValueVisitor visitor);
}
