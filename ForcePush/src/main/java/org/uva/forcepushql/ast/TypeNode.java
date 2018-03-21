package org.uva.forcepushql.ast;

public class TypeNode extends Node
{
    private String type;

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    @Override
    public String accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }
}