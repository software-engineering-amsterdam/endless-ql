package expression;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right){
        super(left, right,
                (form, a, b) ->
                        a.evaluate(form).and(b.evaluate(form))
                , "&&");
    }
}