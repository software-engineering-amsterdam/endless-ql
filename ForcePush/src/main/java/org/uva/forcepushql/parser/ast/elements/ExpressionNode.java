package org.uva.forcepushql.parser.ast.elements;


import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;

public abstract class ExpressionNode extends Node
{
    abstract public <T> T accept(ASTExpressionVisitor visitor);

    public ExpressionNode(boolean isBooleanExpression){
        super(isBooleanExpression);
    }
}

