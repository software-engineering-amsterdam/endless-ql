package com.chariotit.uva.sc.qdsl.ql.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.node.operator.Operator;
import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class UnOpExpression extends Expression {

    private Operator operator;
    private Expression expression;

    public UnOpExpression(Operator operator, Expression expression, Integer lineNumber, Integer
            columnNumber) {
        super(lineNumber, columnNumber);
        this.operator = operator;
        this.expression = expression;
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
        operator.acceptVisitor(visitor);
        expression.acceptVisitor(visitor);

        visitor.visitUnOpExpression(this);
    }
}
