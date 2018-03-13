package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.ParseObjectsQL.Expressions.EvaluationType;

public class StringConstant extends Constant<String> {
    public StringConstant(String value){
        super(value.isEmpty() ? "" : value);
    }

    public EvaluationType returnType(){
        return EvaluationType.String;
    }
}
