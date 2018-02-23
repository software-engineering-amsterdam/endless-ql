package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Identifier extends Expression {
    
    private String name;
    
    public Identifier(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public boolean isIdentifier() {
        return true;
    }
}