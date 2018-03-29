package ql.model.expression.variable;

import ql.IQLVisitor;

import java.time.LocalDate;

public class ExpressionVariableDate extends ExpressionVariable<LocalDate> {

    public ExpressionVariableDate(LocalDate value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}