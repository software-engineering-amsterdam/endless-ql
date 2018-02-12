package expression;

import model.Form;

public class ExpressionBoolean extends Expression<Boolean> {

    private Boolean value;

    public ExpressionBoolean(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean evaluate(Form form) {
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

    @Override
    public boolean isBoolean(Form form){
        return true;
    }

    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public boolean isSetable(Form form){
        return true;
    }
}
