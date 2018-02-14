package expression;

public class ExpressionArithmeticMultiply extends ExpressionArithmetic {

    public ExpressionArithmeticMultiply(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.multiply(form, b),"*");
    }
}
