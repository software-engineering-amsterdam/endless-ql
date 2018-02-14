package expression;

public class ExpressionLogicalOr extends ExpressionLogical {

    public ExpressionLogicalOr(Expression left, Expression right){
        super(left, right,
                (form, a, b) -> a.equals(form, new ExpressionVariableBoolean(true))
                        || b.equals(form, new ExpressionVariableBoolean(true)), "||");
    }
}