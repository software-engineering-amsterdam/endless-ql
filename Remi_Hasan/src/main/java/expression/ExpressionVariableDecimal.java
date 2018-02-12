package expression;

import model.Form;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Double value){
        this.value = value;
    }
    
    @Override
    public Double evaluate(Form form) {
        return value;
    }

    @Override
    public boolean isNumber(Form form){
        return true;
    }

    @Override
    public void setValue(String value){
        this.value = Double.parseDouble(value);
    }

}
