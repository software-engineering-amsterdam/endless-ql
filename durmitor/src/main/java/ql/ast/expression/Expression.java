package ql.ast.expression;

import ql.ast.QLNode;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitable;

public abstract class Expression extends QLNode implements ExpressionVisitable {

    protected Type type;
    
    public abstract String toString();
    
    public abstract Type getType();

    public boolean isIdentifier() {
        return false;
    }
}
