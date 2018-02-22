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
}
