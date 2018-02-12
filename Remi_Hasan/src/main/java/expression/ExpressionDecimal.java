package expression;

import model.Form;

public class ExpressionDecimal extends Expression<Double> {

    private Double value;

    public ExpressionDecimal(Double value){
        this.value = value;
    }
    
    @Override
    public Double evaluate(Form form) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean isNumber(Form form){
        return true;
    }

    @Override
    public void setValue(String value){
        this.value = Double.parseDouble(value);
    }

    @Override
    public boolean isEvaluable(Form form){
        return this.value != null;
    }

    @Override
    public boolean isSetable(Form form){
        return true;
    }
}
