package astvisitor;

public class NumValue extends Value<Number>{
    final Number value;
    NumValue(Number value){
        this.value = value;
    }

    @Override
    public Value divide(BaseASTVisitor visitor, Value other) {
        super.divide(this, other);
    }
}
