package expression;

public class ExpressionComparisonLT extends ExpressionComparison{

    public ExpressionComparisonLT(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.evaluate(form).lt(b.evaluate(form)), "<");
    }
}