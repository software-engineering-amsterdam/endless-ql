package QL.classes.values;

public abstract class NumericValue<T extends Number> extends Value<T>{
    NumericValue(T value){
        super(value);
    }

    @Override
    public T getValue() {
        return super.getValue();
    }

    public abstract double getComputationValue();
}
