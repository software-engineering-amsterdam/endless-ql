package classes.values;

public class MoneyValue extends NumericValue<Double> {
    public MoneyValue(Double value) {
        super(value);
        setType(Value.MONEY);
    }

    public MoneyValue() {
        super(0.0);
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
