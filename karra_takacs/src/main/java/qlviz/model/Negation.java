package qlviz.model;

public class Negation extends BooleanExpression {
    private final BooleanExpression operand;

    @Override
    public boolean evaluate() {
        return !operand.evaluate();
    }

    public Negation(BooleanExpression operand) {
        this.operand = operand;
    }
}

