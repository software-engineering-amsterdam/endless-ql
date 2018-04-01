package qlviz.interpreter;

import qlviz.model.expressions.booleanExpressions.NumericComparisonOperator;

public interface NumericComparisonOperatorTranslator {
    NumericComparisonOperator translate(String operatorString);
}

