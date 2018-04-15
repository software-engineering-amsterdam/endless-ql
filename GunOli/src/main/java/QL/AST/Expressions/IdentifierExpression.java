package QL.AST.Expressions;

import QL.Visitors.ExpressionVisitorInterface;

public class IdentifierExpression extends Expression {
    private String identifier;

    public IdentifierExpression(String id, int line){
        super(line);
        this.identifier = id;
    }

    public String getIdentifier(){ return identifier; }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
