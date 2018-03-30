package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Evaulators.BinaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.NumericValue;
import QL.Evaluation.Values.UndefinedValue;

public class GreaterThanEvaluator extends BinaryEvaluator{
    public GreaterThanEvaluator(Value left, Value right){
        super(left, right);
    }

    public Value evaluate(){
        if(getLeftValue().isArithmetic() && getRightValue().isArithmetic()){
            NumericValue left = (NumericValue) getLeftValue();
            NumericValue right = (NumericValue) getRightValue();

            // BigDecimal .compareTo(): less = -1, greater = 1, equal = 0
            return new BooleanValue(left.getValue().doubleValue() > right.getValue().doubleValue());
        }

        return new UndefinedValue();
    }
}
