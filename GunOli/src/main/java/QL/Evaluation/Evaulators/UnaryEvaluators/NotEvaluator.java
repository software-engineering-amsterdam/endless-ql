package QL.Evaluation.Evaulators.UnaryEvaluators;

import QL.Evaluation.Evaulators.UnaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.BooleanValue;
import QL.Evaluation.Values.UndefinedValue;

public class NotEvaluator extends UnaryEvaluator{
    public NotEvaluator(Value value){
        super(value);
    }

    public Value evaluate(){
        if(getValue().isLogical()){
            BooleanValue oldValue = (BooleanValue) getValue();
            return new BooleanValue(!oldValue.getValue());
        }

        return new UndefinedValue();
    }
}
