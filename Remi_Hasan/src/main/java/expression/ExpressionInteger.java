package expression;

import model.Form;

public class ExpressionInteger extends Expression<Integer> {

    private Integer value;

    public ExpressionInteger(Integer value){
        this.value = value;
    }

    @Override
    public Integer evaluate(Form form) {
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

    @Override
    public void setValue(String answer) {
        this.value = Integer.parseInt(answer);
    }

    @Override
    public boolean isSetable(Form form){
        return true;
    }
}
