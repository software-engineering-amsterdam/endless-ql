package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;

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

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Negation(BooleanExpression operand) {
        this.operand = operand;
    }

    public BooleanExpression getOperand() {
        return operand;
    }
}

