package qlviz.interpreter;

import qlviz.model.expressions.booleanExpressions.BinaryBooleanOperator;

public interface BinaryBooleanOperatorTranslator {
    BinaryBooleanOperator translate(String operatorString);
}

