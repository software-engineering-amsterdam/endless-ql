package ql.logic.validators;

import ql.ast.model.expressions.values.Literal;
import ql.error.Error;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DateFormatValidator extends Validator {
    private List<Literal> literals;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public DateFormatValidator(List<Literal> literals) {
        this.literals = literals;
    }

    @Override
    public boolean validate() {
        for (Literal literal : this.literals) {
            try {
                dateFormat.parse(literal.getValue());
            } catch (ParseException e) {
                this.setError(new Error(Error.Level.CRITICAL, e.getMessage()));
            }
        }
        return true;
    }
}
