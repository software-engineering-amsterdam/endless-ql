package QL.Evaluation.Values;

import QL.Evaluation.Value;

public class BooleanValue extends Value<Boolean> {
    public BooleanValue(Boolean value){
        super(value);
    }

    @Override
    public Boolean isLogical(){
        return true;
    }
}
