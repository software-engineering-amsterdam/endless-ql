package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionLinker;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;

import java.math.BigDecimal;

public interface NumericExpression {

    void accept(NumericExpressionVisitor numericExpressionVisitor);
    <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor);
}


