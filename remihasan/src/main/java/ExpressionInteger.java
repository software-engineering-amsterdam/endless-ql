public class ExpressionInteger extends Expression<Integer> {

    private final int value;

    ExpressionInteger(int value){
        this.value = value;
    }

    @Override
    public Integer evaluate() {
        return value;
    }

    @Override
    public boolean isEvaluable() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
