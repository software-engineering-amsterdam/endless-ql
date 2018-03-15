package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class LabelExpression extends Expression {

    private String label;

    public LabelExpression(String label, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);

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
        visitor.visitLabelExpression(this);
    }
}
