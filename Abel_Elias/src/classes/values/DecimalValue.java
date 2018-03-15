package classes.types;

public class DecimalValue extends Value<Double> {
    DecimalValue(Double value, String type) {
        super(value);
        setType(Value.DECIMAL);
    }
}
