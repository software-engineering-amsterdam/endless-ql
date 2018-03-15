package classes.values;

import java.util.Date;

public class DateValue extends Value<Date> {
    public DateValue(Date value) {
        super(value);
        setType(Value.DATE);
    }

    public DateValue(){
        super(new Date());
    }
}
