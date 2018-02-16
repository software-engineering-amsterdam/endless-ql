package ql.ast.expression;

public abstract class Binary extends Expression {
    
    protected Expression lhs;
    protected Expression rhs;
    
    public Binary(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString()
    {
        return lhs + " op " + rhs;
    }
}
