package ql.ast.expression;

public abstract class Literal<T> extends Primary {
    
    public abstract T getValue();
    
    @Override
    public boolean isLiteral() {
        return true;
    }
}
