package qlviz.interpreter;

import qlviz.model.expressions.numericExpressions.BinaryNumericOperator;

public interface BinaryNumericOperatorTranslator {
    BinaryNumericOperator translate(String s);
}


