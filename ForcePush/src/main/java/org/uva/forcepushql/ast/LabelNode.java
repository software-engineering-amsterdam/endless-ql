package org.uva.forcepushql.ast;

public class LabelNode extends Node{

    private String label;

    public void setLabel (String label) { this.label = label; }

    public String getLabel ()           { return this.label;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}