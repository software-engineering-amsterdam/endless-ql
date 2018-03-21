package QL.classes.values;

public class DecimalValue extends NumericValue<Double> {
    public DecimalValue(Double value) {
        super(value);
        setType(Value.DECIMAL);
    }

    public DecimalValue(){
        this(0.0);
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue((double) o);
    }

    @Override
    public double getComputationValue() {
        return getValue();
    }
}
