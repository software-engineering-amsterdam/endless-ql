package expression;

public class ExpressionArithmeticDivide extends ExpressionArithmetic {

    public ExpressionArithmeticDivide(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.evaluate(form).divide(b.evaluate(form)),"/");
    }
}
