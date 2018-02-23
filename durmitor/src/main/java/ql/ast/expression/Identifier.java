package ql.ast.expression;

import ql.visitors.interfaces.ExpressionVisitor;

public class Identifier extends Primary {
    
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
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public boolean isIdentifier() {
        return true;
    }
}