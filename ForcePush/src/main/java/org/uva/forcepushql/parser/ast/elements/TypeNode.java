package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.ValueType;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

public class TypeNode extends Node
{
    private ValueType type;

    public void setType(ValueType type)
    {
        this.type = type;
    }

    public ValueType getType()
    {
        return this.type;
    }

    public TypeNode(){
        super(false);
    }

    @Override
    public ValueType accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }
}