package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class Label extends AstNode {

    private String label;

    public Label(String label, SourceFilePosition filePosition) {
        super(filePosition);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitLabel(this);
    }
}
