package org.uva.forcepushql.ast;


public abstract class ExpressionNode extends Node{
    abstract public <T> T accept(ASTExpressionVisitor visitor);
}

