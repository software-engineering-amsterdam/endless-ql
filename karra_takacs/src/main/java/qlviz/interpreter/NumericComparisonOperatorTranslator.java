package qlviz.interpreter;

import qlviz.model.NumericComparisonOperator;

public interface NumericComparisonOperatorTranslator {
    NumericComparisonOperator translate(String operatorString);
}

