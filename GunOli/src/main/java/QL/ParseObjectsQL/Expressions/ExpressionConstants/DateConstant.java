package QL.ParseObjectsQL.Expressions.ExpressionConstants;

import QL.ParseObjectsQL.Expressions.EvaluationType;

import java.time.LocalDate;

public class DateConstant extends Constant<LocalDate> {
    public DateConstant(LocalDate value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Date;
    }

    @Override
    public String toString(){
        return this.getValue().toString();
    }
}
