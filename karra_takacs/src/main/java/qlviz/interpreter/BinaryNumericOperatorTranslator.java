package qlviz.interpreter;

import qlviz.model.BinaryNumericOperator;

public interface BinaryNumericOperatorTranslator {
    BinaryNumericOperator translate(String s);
}


