package ql.ast.expression;

public abstract class Literal<T> extends Expression {
    
    public abstract T getValue();
}
