package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.ParseObjectsQL.Expressions.EvaluationType;

public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Number value){
        super(value == null ? 0.00 : value.doubleValue());
    }

    public EvaluationType returnType(){
        return EvaluationType.Money;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }

    @Override
    public String toString(){
        return Double.toString(this.getValue());
    }
}
