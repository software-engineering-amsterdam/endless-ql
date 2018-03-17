package classes.values;

public class BooleanValue extends Value<Boolean> {
    public BooleanValue(Boolean value) {
        super(value);
        setType(Value.BOOLEAN);
    }

    public BooleanValue(){
        this(false);
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue((boolean) o );
    }
}
