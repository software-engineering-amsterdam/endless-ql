package com.chariotit.uva.sc.qdsl.ast.node;

public class LabelExpression extends Expression {

    private Label label;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public LabelExpression(Label label) {

        this.label = label;
    }
}
