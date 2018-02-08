public class ExpressionDate extends Expression<String>{
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
