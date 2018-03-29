package qlviz.model.expressions.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.expressions.Expression;

public interface NumericExpression extends Expression {

    void accept(NumericExpressionVisitor numericExpressionVisitor);
    <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor);
}


