package expression;

public class ExpressionComparisonLE extends ExpressionComparison {

    public ExpressionComparisonLE(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.evaluate(form).le(b.evaluate(form)), "<=");
    }
}