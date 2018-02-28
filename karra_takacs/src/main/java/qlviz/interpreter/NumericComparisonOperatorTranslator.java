package qlviz.interpreter;

import qlviz.model.booleanExpressions.NumericComparisonOperator;

public interface NumericComparisonOperatorTranslator {
    NumericComparisonOperator translate(String operatorString);
}

