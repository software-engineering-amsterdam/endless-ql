package expression;

public class ExpressionArithmeticSubtract extends ExpressionArithmetic {

    public ExpressionArithmeticSubtract(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.subtract(form, b),"-");
    }
}
