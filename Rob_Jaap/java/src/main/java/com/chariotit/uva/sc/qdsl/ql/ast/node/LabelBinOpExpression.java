package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.node.operator.Operator;
import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class LabelBinOpExpression extends Expression {

    private LabelExpression labelExpression;
    private Operator operator;
    private Expression expression;

    public LabelBinOpExpression(LabelExpression labelExpression, Operator operator, Expression expression,
                                Integer
            lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.labelExpression = labelExpression;
        this.operator = operator;
        this.expression = expression;
    }

    public LabelExpression getLabelExpression() {
        return labelExpression;
    }

    public void setLabelExpression(LabelExpression labelExpression) {
        this.labelExpression = labelExpression;
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

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        labelExpression.acceptVisitor(visitor);
        operator.acceptVisitor(visitor);
        expression.acceptVisitor(visitor);

        visitor.visitLabelBinOpExpression(this);
    }
}
