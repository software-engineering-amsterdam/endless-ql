package expression;

public class ExpressionComparisonGE extends ExpressionComparison {

    public ExpressionComparisonGE(Expression left, Expression right){
        super(left, right, (form, a, b) -> a.ge(form, b), ">=");
    }
}
