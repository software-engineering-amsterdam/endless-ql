package expression;

import model.Form;

public class ExpressionInteger extends Expression<Integer> {

    private final int value;

    public ExpressionInteger(int value){
        this.value = value;
    }

    @Override
    public Integer evaluate(Form form) {
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
