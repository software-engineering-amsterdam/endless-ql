package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class Negation implements BooleanExpression {
    private final BooleanExpression operand;

    @Override
    public boolean evaluate() {
        return !operand.evaluate();
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public Negation(BooleanExpression operand) {
        this.operand = operand;
    }

    public BooleanExpression getOperand() {
        return operand;
    }
}

