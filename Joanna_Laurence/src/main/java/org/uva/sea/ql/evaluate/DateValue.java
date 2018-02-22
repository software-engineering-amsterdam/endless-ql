package org.uva.sea.ql.evaluate;


import org.uva.sea.ql.QLValueEvaluator;

import java.util.Calendar;

public class DateValue extends Value {

    private Calendar dateValue;

    public DateValue(Calendar dateValue) {

        this.dateValue = dateValue;
    }

    public Calendar getDateValue() {
        return dateValue;
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }
}
