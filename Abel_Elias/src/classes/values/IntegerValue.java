package classes.types;

public class IntegerValue extends Value<Boolean> {
    IntegerValue(Boolean value, String type) {
        super(value);
        setType(Value.INTEGER);
    }
}
