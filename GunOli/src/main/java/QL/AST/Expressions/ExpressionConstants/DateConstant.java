package QL.AST.Expressions.ExpressionConstants;

import QL.AST.Expressions.Constant;
import QL.Visitors.ExpressionVisitorInterface;

import java.time.LocalDate;

public class DateConstant extends Constant<LocalDate> {
    public DateConstant(LocalDate value, int line){
        super(value == null ? LocalDate.now(): value, line);
    }

    @Override
    public String toString(){
        return this.getValue().toString();
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
