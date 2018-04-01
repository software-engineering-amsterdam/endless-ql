package QL.classes.values;

public class MoneyValue extends NumericValue<Double> {
    public MoneyValue(Double value) {
        super(value);
        setType(Value.MONEY);
    }

    public MoneyValue() {
        this(0.0);
    }

    @Override
    public void setValueGeneric(Object obj) {
        setValue(((Number) obj).doubleValue());
    }

    @Override
    public double getComputationValue() {
        return getValue();
    }
}
