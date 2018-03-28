package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.Analysis.EvaluationType;

public class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value, int line){
        super(value == null ? 0.0 : value, line);
    }
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }

    @Override
    public String toString(){
        return Double.toString(this.getValue());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
