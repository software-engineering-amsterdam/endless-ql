package ql.ast.expression;

public abstract class Unary extends Expression {
    
    protected Expression expr;
    
    @Override
    public String toString()
    {
        return expr+"";
    }
}
