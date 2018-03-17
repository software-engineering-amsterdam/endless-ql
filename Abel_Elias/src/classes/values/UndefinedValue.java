package classes.values;

public class UndefinedValue extends Value {

    public UndefinedValue(String value) {
        super(value);
    }

    public UndefinedValue() {
        this(null);
    }

    @Override
    public void setValueGeneric(Object o) {

    }
}
