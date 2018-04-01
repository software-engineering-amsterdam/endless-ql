package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.Node;

public class LabelNode extends Node
{

    private String label;

    public LabelNode(){
        super(false);
    }

    public void setLabel (String label) { this.label = label; }

    public String getLabel ()           { return this.label;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}