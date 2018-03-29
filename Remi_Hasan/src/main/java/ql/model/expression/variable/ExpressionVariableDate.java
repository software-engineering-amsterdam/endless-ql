package ql.model.expression.variable;

import ql.IQLVisitor;

import org.antlr.v4.runtime.Token;

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