package qlviz.model.expressions.booleanExpressions;

import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.expressions.Expression;

public interface BooleanExpression extends Expression {
    public void accept(BooleanExpressionVisitor visitor);
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor);
}

