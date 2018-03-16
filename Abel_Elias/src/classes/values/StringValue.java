package classes.values;

public class StringValue extends Value<String> {
    public StringValue(String value) {
        super(value);
        setType(Value.STRING);
    }

    public StringValue() {
        this("");
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue((String) o);
    }
}
