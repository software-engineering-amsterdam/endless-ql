package QL.Evaluation.Evaulators.UnaryEvaluators;

import QL.Evaluation.Evaulators.UnaryEvaluator;
import QL.Evaluation.Value;
import QL.Evaluation.Values.NumericValue;
import QL.Evaluation.Values.UndefinedValue;

import java.math.BigDecimal;

public class NegationEvaluator extends UnaryEvaluator{
    public NegationEvaluator(Value value){
        super(value);
    }

    public Value evaluate(){
        if(getValue().isArithmetic()){
            NumericValue oldValue = (NumericValue) getValue();
            return new NumericValue(oldValue.getValue().doubleValue() * -1);
        }

        return new UndefinedValue();
    }
}
