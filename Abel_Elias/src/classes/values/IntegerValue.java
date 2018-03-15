package classes.values;

public class IntegerValue extends Value<Integer> {
    public IntegerValue(Integer value) {
        super(value);
        setType(Value.INTEGER);
    }

    public IntegerValue(){
        this(0);
    }
}
