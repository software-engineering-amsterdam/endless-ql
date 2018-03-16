package classes.values;

public class DecimalValue extends NumericValue<Double> {
    public DecimalValue(Double value) {
        super(value);
        setType(Value.DECIMAL);
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue((double) o);
    }

    @Override
    public double getComputationValue() {
        return getValue();
    }

    public DecimalValue(){
        this(0.0);
    }
}
