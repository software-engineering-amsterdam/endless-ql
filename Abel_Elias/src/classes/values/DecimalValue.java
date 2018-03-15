package classes.values;

public class DecimalValue extends Value<Double> {
    public DecimalValue(Double value) {
        super(value);
        setType(Value.DECIMAL);
    }

    public DecimalValue(){
        this(0.0);
    }
}
