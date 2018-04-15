package QL.AST.Expressions.ExpressionConstants;

import QL.AST.Expressions.Constant;
import QL.Analysis.ExpressionVisitorInterface;

public class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value, int line){
        super(value == null ? 0.0 : value, line);
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
