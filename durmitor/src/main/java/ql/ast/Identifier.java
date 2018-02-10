package ql.ast;

import ql.ast.expression.Expression;

public class Identifier extends Expression {
    
    private String name;
    
    public Identifier(String name) {
        this.name = name;
    }
    
    }
