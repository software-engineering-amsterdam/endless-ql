package org.uva.sea.ql.evaluate;


import java.util.Calendar;

public class DateValue extends Value {

    private Calendar dateValue;

    public DateValue(Calendar dateValue) {

        this.dateValue = dateValue;
    }

    public Calendar getDateValue() {
        return dateValue;
    }

    public Value add(IntValue value) {
        return new ErrorValue("Cannot add integer to date");
    }

    public Value add(DecimalValue value) {
        return new ErrorValue("Cannot add decimal to date");
    }

    public Value add(MoneyValue value) {
        return new ErrorValue("Cannot add money to date");
    }

    public Value add(BooleanValue value) {
        return new ErrorValue("Cannot add boolean to date");
    }

    public Value add(DateValue value) {
        return new ErrorValue("Cannot add date to date");
    }

    public Value add(StringValue value) {
        return new ErrorValue("Cannot add string to date");
    }
}
