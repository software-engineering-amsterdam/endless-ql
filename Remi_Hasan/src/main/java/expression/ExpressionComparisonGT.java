package expression;

public class ExpressionComparisonGT extends ExpressionComparison{

    public ExpressionComparisonGT(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.evaluate(form).gt(b.evaluate(form)), ">");
    }
}
