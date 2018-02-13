package expression;

public class ExpressionArithmeticDivide extends ExpressionArithmetic {

    public ExpressionArithmeticDivide(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.divide(form, b),"/");
    }
}
