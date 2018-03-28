package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.Node;

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