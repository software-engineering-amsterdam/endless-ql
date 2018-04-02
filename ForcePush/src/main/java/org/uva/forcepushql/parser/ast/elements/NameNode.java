package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.Node;

public class NameNode extends Node
{
    private String name;

    public NameNode(){
        super(true);
    }
    public void setName (String name) { this.name = name; }

    public String getName ()          { return this.name;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}