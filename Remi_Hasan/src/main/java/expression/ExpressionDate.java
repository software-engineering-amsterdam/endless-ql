package expression;

import model.Form;

public class ExpressionDate extends Expression<String> {
    // TODO: figure out best way to save and/or validate a date

    private String value;

    public ExpressionDate(String value) {
        this.value = value;
    }

    @Override
    public String evaluate(Form form) {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void setValue(String value){
        this.value = value;
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
