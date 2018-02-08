public class ExpressionBoolean extends Expression<Boolean> {

    private final boolean value;

    ExpressionBoolean(boolean value){
        this.value = value;
    }
    
    @Override
    public Boolean evaluate() {
        return this.value;
    }
}
