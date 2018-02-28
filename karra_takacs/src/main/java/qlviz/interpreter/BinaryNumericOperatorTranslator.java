package qlviz.interpreter;

import qlviz.model.numericExpressions.BinaryNumericOperator;

public interface BinaryNumericOperatorTranslator {
    BinaryNumericOperator translate(String s);
}


