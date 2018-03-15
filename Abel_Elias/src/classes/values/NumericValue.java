package classes.values;

public abstract class NumericValue<T extends Number> extends Value<T>{
    NumericValue(T value){
        super(value);
    }
}
