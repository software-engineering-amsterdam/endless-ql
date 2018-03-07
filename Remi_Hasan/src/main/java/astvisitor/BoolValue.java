package astvisitor;

public class BoolValue extends Value<Boolean>{
    public BoolValue(Boolean value){
        super(value);
    }
    @Override
    public NumValue divide(Value right) {
        throw new UnsupportedOperationException("Cannot perform divide on boolean.");
    }

    @Override
    public NumValue multiply(Value right) {
        throw new UnsupportedOperationException("Cannot perform multiply on boolean.");
    }

    @Override
    public NumValue subtract(Value right) {
        throw new UnsupportedOperationException("Cannot perform subtract on boolean.");
    }

    @Override
    public NumValue sum(Value right) {
        throw new UnsupportedOperationException("Cannot perform sum on boolean.");
    }

    @Override
    public BoolValue eq(Value right) {
        return new BoolValue(this.value.equals(right.value));
    }

    @Override
    public BoolValue ge(Value right) {
        throw new UnsupportedOperationException("Cannot perform ge on boolean.");
    }

    @Override
    public BoolValue gt(Value right) {
        throw new UnsupportedOperationException("Cannot perform gt on boolean.");
    }

    @Override
    public BoolValue le(Value right) {
        throw new UnsupportedOperationException("Cannot perform le on boolean.");
    }

    @Override
    public BoolValue lt(Value right) {
        throw new UnsupportedOperationException("Cannot perform lt on boolean.");
    }

    @Override
    public BoolValue and(Value right) {
        BoolValue rightValue = (BoolValue) right;
        return new BoolValue(this.value && rightValue.value);
    }

    @Override
    public BoolValue or(Value right) {
        BoolValue rightValue = (BoolValue) right;
        return new BoolValue(this.value || rightValue.value);
    }

    @Override
    public BoolValue not() {
        return new BoolValue(!this.value);
    }

    @Override
    public NumValue neg() {
        throw new UnsupportedOperationException("Cannot perform neg on boolean.");
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof BoolValue){
            BoolValue otherValue = (BoolValue) other;
            return otherValue.value.equals(otherValue.value);
        }
        return false;
    }
}
