package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionLinker;
import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public abstract class NumericExpression {
    public abstract BigDecimal evaluate();

    public abstract void accept(NumericExpressionVisitor numericExpressionVisitor);
}


