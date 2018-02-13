package expression;

public class ExpressionComparisonEq extends ExpressionComparison {

    public ExpressionComparisonEq(Expression left, Expression right) {
        super(left, right, (form, a, b) -> a.equals(form, b), "==");
    }
}
