package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class LabelExpression extends Expression {

    private Label label;

    public LabelExpression(Label label) {

        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }


    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        label.acceptVisitor(visitor);

        visitor.visitLabelExpression(this);
    }
}
