package expression;

public class ExpressionComparisonGT extends ExpressionComparison{

    public ExpressionComparisonGT(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.gt(form, b), ">");
    }
}
