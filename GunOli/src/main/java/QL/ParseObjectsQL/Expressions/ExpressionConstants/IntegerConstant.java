package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.ParseObjectsQL.Expressions.EvaluationType;

public class IntegerConstant extends Constant<Integer> {
    public IntegerConstant(Integer value){
        super(value == null ? 0 : value);
    }


    public EvaluationType returnType(){
        return EvaluationType.Integer;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
