package expression;

public class ExpressionDate extends Expression<String> {
    // TODO: figure out best way to save and/or validate a date

    private final String text;

    public ExpressionDate(String text) {
        this.text = text;
    }

    @Override
    public String evaluate() {
        return text;
    }

    @Override
    public boolean isEvaluable() {
        return true;
    }

    @Override
    public String toString() {
        return text;
    }
}
