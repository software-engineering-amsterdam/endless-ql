package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Evaulators.BinaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.NumericValue;
import QL.Evaluation.Values.UndefinedValue;

public class AdditionEvaluator extends BinaryEvaluator{
    public AdditionEvaluator(Value left, Value right){
        super(left, right);
    }

    public Value evaluate(){
        if(getLeftValue().isArithmetic() && getRightValue().isArithmetic()){
            NumericValue left = (NumericValue) getLeftValue();
            NumericValue right = (NumericValue) getRightValue();

            return new NumericValue(left.getValue().doubleValue() + right.getValue().doubleValue());
        }

        return new UndefinedValue();
    }
}
