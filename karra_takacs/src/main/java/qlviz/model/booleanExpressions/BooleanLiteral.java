package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;

public class BooleanLiteral implements BooleanExpression {

    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    @Override
    public boolean evaluate() {
        return this.value;
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }
}

