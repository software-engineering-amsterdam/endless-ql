package ql.ast.expression;

import ql.ast.QLNode;
import ql.visitors.interfaces.ExpressionVisitable;

public abstract class Expression extends QLNode implements ExpressionVisitable {

    public boolean isUnaryOperator() {
        return false;
    }
    
    public boolean isBinaryOperator() {
        return false;
    }
    
    public boolean isPrimary() {
        return false;
    }
    
    public boolean isLiteral() {
        return false;
    }
    
    public boolean isIdentifier() {
        return false;
    }
}
