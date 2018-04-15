package QL.AST.Expressions.ExpressionConstants;

import QL.AST.Expressions.Constant;
import QL.Analysis.ExpressionVisitorInterface;

public class BooleanConstant extends Constant<Boolean> {
    public BooleanConstant(Boolean value, int line) { super(value == null ? false : value, line); }

    @Override
    public Boolean isLogical(){
        return true;
    }

    @Override
    public String toString(){
        return Boolean.toString(this.getValue());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
