package QL.Evaluation.Values;

import QL.Evaluation.Value;

public class NumericValue extends Value<Number> {
    public NumericValue(Number value){
        super(value);
    }

    @Override
    public Boolean isArithmetic(){ return true; }
}
