package qlviz.interpreter;

import qlviz.model.booleanExpressions.BinaryBooleanOperator;

public interface BinaryBooleanOperatorTranslator {
    BinaryBooleanOperator translate(String operatorString);
}

