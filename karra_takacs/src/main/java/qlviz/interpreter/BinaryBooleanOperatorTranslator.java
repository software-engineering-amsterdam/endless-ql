package qlviz.interpreter;

import qlviz.model.BinaryBooleanOperator;
import qlviz.model.NumericComparisonOperator;

public interface BinaryBooleanOperatorTranslator {
    BinaryBooleanOperator translate(String operatorString);
}

