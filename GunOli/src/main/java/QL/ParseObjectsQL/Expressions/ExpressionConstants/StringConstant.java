package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.Analysis.EvaluationType;

public class StringConstant extends Constant<String> {
    public StringConstant(String value, int line){
        super(value == null ? "" : value, line);
    }

    public EvaluationType returnType(){
        return EvaluationType.String;
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
