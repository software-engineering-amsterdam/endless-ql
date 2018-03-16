package classes.values;

public class IntegerValue extends NumericValue<Integer> {
    public IntegerValue(Integer value) {
        super(value);
        setType(Value.INTEGER);
    }

    @Override
    public void setValueGeneric(Object o) {
        setValue((int) o);
    }

    @Override
    public double getComputationValue() {
        return (double) getValue();
    }

    public IntegerValue(){
        this(0);
    }
}
