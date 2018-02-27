package ql.ast.expression;

public abstract class Primary extends Expression {
    
    public abstract String toString();
    
    @Override
    public boolean isPrimary() {
        return true;
    }
}
