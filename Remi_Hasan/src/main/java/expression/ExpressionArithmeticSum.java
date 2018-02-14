package expression;

public class ExpressionArithmeticSum extends ExpressionArithmetic {

    public ExpressionArithmeticSum(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.sum(form, b),"+");
    }
}