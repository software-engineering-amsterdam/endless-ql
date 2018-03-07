package org.uva.sea.evaluate.valueTypes;


import org.uva.sea.exceptions.EvaluationException;
import org.uva.sea.ql.NodeType;
import org.uva.sea.visitor.BaseValueVisitor;

import java.security.InvalidParameterException;
import java.util.Calendar;

public class DateValue extends Value {

    private Calendar dateValue;

    public DateValue(String date) {
        String[] dateSplit = date.split(date, 3);
        if (dateSplit.length != 3)
            throw new InvalidParameterException("Incorrect date: " + date);

        this.dateValue.set(Calendar.YEAR, Integer.parseInt(dateSplit[2]));
        this.dateValue.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]));
        this.dateValue.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[0]));
    }

    public DateValue(Calendar dateValue) {
        this.dateValue = dateValue;
    }

    public Calendar getDateValue() {
        return dateValue;
    }

    @Override
    public Value isEqual(Value value) throws EvaluationException {
        return value.isEqual(this);
    }

    @Override
    public Value isEqual(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare == 0);
    }

    @Override
    public Value isGreaterOrEqual(Value value) throws EvaluationException {
        return value.isLessThan(this);
    }

    @Override
    public Value isGreaterOrEqual(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare == 0 || compare > 0);
    }

    @Override
    public Value isGreaterThan(Value value) throws EvaluationException {
        return value.isLessOrEqual(this);
    }

    @Override
    public Value isGreaterThan(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare == 0);
    }

    @Override
    public Value isLessOrEqual(Value value) throws EvaluationException {
        return value.isGreaterThan(this);
    }

    @Override
    public Value isLessOrEqual(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare == 0 || compare < 0);
    }

    @Override
    public Value isLessThan(Value value) throws EvaluationException {
        return value.isGreaterOrEqual(this);
    }

    @Override
    public Value isLessThan(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare < 0);
    }

    @Override
    public Value isNotEqual(Value value) throws EvaluationException {
        return value.isNotEqual(this);
    }

    @Override
    public Value isNotEqual(DateValue value) {
        int compare = this.dateValue.compareTo(value.getDateValue());
        return new BooleanValue(compare != 0);

    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.DATE;
    }
}
