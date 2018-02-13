package expression;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right){
        super(left, right,
                (form, a, b) -> a.equals(form, new ExpressionVariableBoolean(true)) && b.equals(form, new ExpressionVariableBoolean(true)), "&&");
    }
}