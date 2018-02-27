package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;

import java.math.BigDecimal;

public interface BooleanExpression {
    public boolean evaluate();
    public void accept(BooleanExpressionVisitor visitor);
}

