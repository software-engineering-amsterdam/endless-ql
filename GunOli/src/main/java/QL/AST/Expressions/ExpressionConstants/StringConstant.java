package QL.AST.Expressions.ExpressionConstants;

import QL.AST.Expressions.Constant;
import QL.Visitors.ExpressionVisitorInterface;

public class StringConstant extends Constant<String> {
    public StringConstant(String value, int line){
        super(value == null ? "" : value, line);
    }

    @Override
    public String toString(){
        return this.getValue();
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
