package expression;

public class ExpressionLogicalOr extends ExpressionLogical {

    public ExpressionLogicalOr(Expression left, Expression right){
        super(left, right,
                (form, a, b) -> a.evaluate(form).or(b.evaluate(form)), "||");
    }
}