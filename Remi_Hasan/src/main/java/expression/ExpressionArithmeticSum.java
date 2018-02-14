package expression;

public class ExpressionArithmeticSum extends ExpressionArithmetic {

    public ExpressionArithmeticSum(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.evaluate(form).sum(b.evaluate(form)),"+");
    }
}