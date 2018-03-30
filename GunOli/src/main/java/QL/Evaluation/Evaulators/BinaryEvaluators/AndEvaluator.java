package QL.Evaluation.Evaulators.BinaryEvaluators;

import QL.Evaluation.Evaulators.BinaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.UndefinedValue;


public class AndEvaluator extends BinaryEvaluator {
    public AndEvaluator(Value left, Value right){
        super(left, right);
    }

    public Value evaluate(){
        if(getLeftValue().isLogical() && getRightValue().isLogical()){
            BooleanValue left = (BooleanValue) getLeftValue();
            BooleanValue right = (BooleanValue) getRightValue();

            return new BooleanValue(left.getValue() && right.getValue());
        }

        return new UndefinedValue();
    }
}
