package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.node.operator.Operator;

public class ConstBinOpExpression extends Expression {

    private Constant constant;
    private Operator operator;
    private Expression expression;

    public ConstBinOpExpression(Constant constant, Operator operator, Expression expression) {
        this.constant = constant;
        this.operator = operator;
        this.expression = expression;
    }

    public Constant getConstant() {
        return constant;
    }

    public void setConstant(Constant constant) {
        this.constant = constant;
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
