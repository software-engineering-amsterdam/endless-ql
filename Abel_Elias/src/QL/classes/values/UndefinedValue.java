package QL.classes.values;

public class UndefinedValue extends NumericValue {

    public UndefinedValue(int value) {
        super(value);
        setType(Value.UNDEFINED);
    }

    public UndefinedValue() {
        this(0);
    }

    @Override
    public boolean isDefined () {
        return false;
    }

    @Override
    public void setValueGeneric(Object o) {

    }

    @Override
    public double getComputationValue() {
        return 0;
    }
}
