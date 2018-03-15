package classes.types;

public class StringValue extends Value<String> {
    StringValue(String value, String type) {
        super(value);
        setType(Value.STRING);
    }
}
