package ql.ast.expression;

import ql.ast.type.Type;
import ql.ast.type.Undefined;

public abstract class Literal<T> extends Primary {
    
    public abstract T getValue();
    
    @Override
    public boolean isLiteral() {
        return true;
    }

    public Type getType() {
        return new Undefined();
    }
}
