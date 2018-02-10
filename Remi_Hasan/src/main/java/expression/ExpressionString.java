package expression;

import model.Form;

public class ExpressionString extends Expression<String> {

    private final String text;

    public ExpressionString(String text) {
        this.text = text;
    }

    @Override
    public String evaluate(Form form) {
        return text;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return true;
    }

    @Override
    public String toString() {
        return text;
    }
}
