package expression;

import model.Form;

public class ExpressionMoney extends Expression<Double> {

    private Double value;

    public ExpressionMoney(Double value){
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
    public boolean isEvaluable(Form form){
        return this.value != null;
    }
}
