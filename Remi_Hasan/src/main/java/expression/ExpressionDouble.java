package expression;

import model.Form;

public class ExpressionDouble extends Expression<Double> {

    private final double value;

    public ExpressionDouble(double value){
        this.value = value;
    }
    
    @Override
    public Double evaluate(Form form) {
        return value;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
