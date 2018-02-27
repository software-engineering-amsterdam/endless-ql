package ql.ast.expression;

import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.value.Value;

public abstract class Literal extends Primary {
    
    public abstract Value<?> getValue();
    
    @Override
    public boolean isLiteral() {
        return true;
    }

    public Type getType() {
        return new Undefined();
    }
}
