package qlviz.model.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;

import java.math.BigDecimal;

public interface BooleanExpression {
    public void accept(BooleanExpressionVisitor visitor);
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor);
}

