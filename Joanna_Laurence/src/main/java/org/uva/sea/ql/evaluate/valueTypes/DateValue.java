package org.uva.sea.ql.evaluate.valueTypes;


import org.uva.sea.ql.visitor.BaseValueVisitor;
import org.uva.sea.ql.parser.NodeType;

import java.security.InvalidParameterException;
import java.util.Calendar;

public class DateValue extends Value {

    private Calendar dateValue;

    public DateValue(String date) {
        String[] dateSplit = date.split(date, 3);
        if(dateSplit.length != 3)
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
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.DATE;
    }
}
