package qlviz.interpreter;

import com.google.inject.Inject;
import qlviz.model.booleanExpressions.NumericComparisonOperator;

public class NumericComparisonOperatorVisitor implements NumericComparisonOperatorTranslator {

    @Inject
    public NumericComparisonOperatorVisitor(){}

    @Override
    public NumericComparisonOperator translate(String operatorString) {
        if (operatorString.equals("==")) {
            return NumericComparisonOperator.Equal;
        }
        else if (operatorString.equals("!=")) {
            return NumericComparisonOperator.NotEqual;
        }
        else if (operatorString.equals("<")) {
            return NumericComparisonOperator.Smaller;
        }
        else if (operatorString.equals("<=")) {
            return NumericComparisonOperator.SmallerOrEqual;
        }
        else if (operatorString.equals(">")) {
            return NumericComparisonOperator.Greater;
        }
        else if (operatorString.equals(">=")) {
            return NumericComparisonOperator.GreaterOrEqual;
        }
        throw new IllegalArgumentException();
    }
}
