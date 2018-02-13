package expression;

public class ExpressionComparisonLT extends ExpressionComparison{

    public ExpressionComparisonLT(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.lt(form, b), "<");
    }
}