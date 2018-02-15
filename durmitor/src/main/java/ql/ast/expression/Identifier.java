package ql.ast.expression;

public class Identifier extends Expression {
    
    private String name;
    
    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}