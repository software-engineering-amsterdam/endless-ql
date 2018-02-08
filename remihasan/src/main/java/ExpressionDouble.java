public class ExpressionDouble extends Expression<Double>{

    private final double value;

    ExpressionDouble(double value){
        this.value = value;
    }
    
    @Override
    public Double evaluate() {
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
