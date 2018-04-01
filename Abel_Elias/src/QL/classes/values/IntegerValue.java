package QL.classes.values;

public class IntegerValue extends NumericValue<Integer> {
    public IntegerValue(Integer value) {
        super(value);
        setType(Value.INTEGER);
    }

    public IntegerValue() {
        this(0);
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue(((Number) o).intValue());
    }

    @Override
    public double getComputationValue() {
        return (double) getValue();
    }
}
