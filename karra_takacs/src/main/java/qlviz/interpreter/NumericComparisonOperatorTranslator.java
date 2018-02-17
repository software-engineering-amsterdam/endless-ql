package qlviz.interpreter;

import qlviz.model.NumericComparisonOperator;

public interface NumericComparisonOperatorTranslator {
    NumericComparisonOperator translate(String operatorString);
}

public class NumericComparisonOperatorVisitor implements NumericComparisonOperatorTranslator {
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
