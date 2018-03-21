package org.uva.forcepushql.ast;

public class NameNode extends Node{
    private String name;

    public void setName (String name) { this.name = name; }

    public String getName ()          { return this.name;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}