package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Evaulators.BinaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.UndefinedValue;

public class EqualsEvaluator extends BinaryEvaluator{
    public EqualsEvaluator(Value left, Value right){
        super(left, right);
    }

    public Value evaluate(){
        if((getLeftValue() != null) && (getRightValue() != null)){
            Value left = getLeftValue();
            Value right = getRightValue();

            return new BooleanValue(left.equals(right));
        }

        return new UndefinedValue();
    }
}
