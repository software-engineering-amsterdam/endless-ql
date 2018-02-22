package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.node.operator.Operator;

public class LabelBinOpExpression extends Expression {

    private Label label;
    private Operator operator;
    private Expression expression;

    public LabelBinOpExpression(Label label, Operator operator, Expression expression) {
        this.label = label;
        this.operator = operator;
        this.expression = expression;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
