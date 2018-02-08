public class ExpressionString extends Expression<String> {

    private final String text;

    public ExpressionString(String text) {
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
