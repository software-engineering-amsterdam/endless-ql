package astvisitor;

import java.util.Date;

public class DateValue extends Value<String>{
    DateValue(String value) {
        super(value);
    }

    @Override
    public NumValue divide(Value right) {
        return null;
    }

    @Override
    public NumValue multiply(Value right) {
        return null;
    }

    @Override
    public NumValue subtract(Value right) {
        return null;
    }

    @Override
    public NumValue sum(Value right) {
        return null;
    }

    @Override
    public BoolValue eq(Value right) {
        return null;
    }

    @Override
    public BoolValue ge(Value right) {
        return null;
    }

    @Override
    public BoolValue gt(Value right) {
        return null;
    }

    @Override
    public BoolValue le(Value right) {
        return null;
    }

    @Override
    public BoolValue lt(Value right) {
        return null;
    }

    @Override
    public BoolValue and(Value right) {
        return null;
    }

    @Override
    public BoolValue or(Value right) {
        return null;
    }

    @Override
    public BoolValue not() {
        return null;
    }

    @Override
    public NumValue neg() {
        return null;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof DateValue){
            DateValue otherValue = (DateValue) other;
            return otherValue.value.equals(otherValue.value);
        }
        return false;
    }
}
