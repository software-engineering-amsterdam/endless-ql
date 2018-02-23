package ql.ast.expression;

import ql.value.Undefined;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class Identifier extends Expression {
    
    private String name;
    private Value<?> value;
    
    public Identifier(String name) {
        this.name   = name;
        this.value  = new Undefined();
    }
    
    public String getName() {
        return name;
    }
    
    public Value<?> getValue() {
        return value;
    }
    
    public void setValue(Value<?> value) {
        this.value = value;
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