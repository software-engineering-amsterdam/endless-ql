package ql.ast.expression;

import java.util.Map;

import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.visitors.interfaces.ExpressionVisitor;

public class Identifier extends Expression {
    
    private String name;
    
    public Identifier(String name) {
        this.name = name;
        super.type = new Undefined();
    }
    
    public String getName() {
        return name;
    }
    
    public void setType(Type type) {
        super.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    public Type getType(Map<String,Type> symbolTable) {
        return symbolTable.getOrDefault(name, type);
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public boolean isIdentifier() {
        return true;
    }
}