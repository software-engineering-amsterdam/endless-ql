package ql.model.expression.variable;

import ql.evaluation.IExpressionVisitor;
import ql.model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ExpressionVariableDate extends ExpressionVariable<LocalDate> {

    public ExpressionVariableDate(Token start, LocalDate value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}