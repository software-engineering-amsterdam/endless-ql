package classes.types;

import java.util.Date;

public class DateValue extends Value<Date> {
    DateValue(Date value, String type) {
        super(value);
        setType(Value.DATE);
    }
}
