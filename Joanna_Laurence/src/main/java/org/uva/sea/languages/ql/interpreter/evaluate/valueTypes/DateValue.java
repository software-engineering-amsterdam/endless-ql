package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;


import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateValue extends Value {

    private final Calendar dateValue;

    public DateValue(final String date) {
        final String[] dateSplit = date.split(date, 3);
        if (dateSplit.length != 3)
            throw new InvalidParameterException("Incorrect date: " + date);

        this.dateValue = Calendar.getInstance();
        this.dateValue.set(Calendar.YEAR, Integer.parseInt(dateSplit[2]));
        this.dateValue.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]));
        this.dateValue.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[0]));
    }

    public DateValue(final Calendar dateValue) {
        this.dateValue = dateValue;
    }

    private Calendar getDateValue() {
        return this.dateValue;
    }

    @Override
    public final Value isEqual(final Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public final Value isEqual(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue(compare == 0);
    }

    @Override
    public final Value isGreaterOrEqual(final Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public final Value isGreaterOrEqual(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue((compare == 0) || (compare > 0));
    }

    @Override
    public final Value isGreaterThan(final Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public final Value isGreaterThan(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue((compare > 0));
    }

    @Override
    public final Value isLessOrEqual(final Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public final Value isLessOrEqual(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue((compare == 0) || (compare < 0));
    }

    @Override
    public final Value isLessThan(final Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public final Value isLessThan(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue(compare < 0);
    }

    @Override
    public final Value isNotEqual(final Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public final Value isNotEqual(final DateValue value) {
        final int compare = this.dateValue.compareTo(value.dateValue);
        return new BooleanValue(compare != 0);

    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.DATE;
    }

    @Override
    public final String toString() {
        if (this.dateValue == null)
            return "";

        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH);
        return formatter.format(this.dateValue.getTime());
    }

    public final DateValue clone() throws CloneNotSupportedException {
        return (DateValue) super.clone();
    }
}
