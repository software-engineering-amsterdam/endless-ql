package ql.model.expression.constant;

import ql.visitor.IQLVisitor;

import java.time.LocalDate;

public class DateConstant extends Constant<LocalDate> {

    public DateConstant(LocalDate value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}