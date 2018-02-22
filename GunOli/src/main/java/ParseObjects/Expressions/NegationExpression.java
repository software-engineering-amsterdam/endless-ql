package ParseObjects.Expressions;

public class NegationExpression extends UnaryExpression<Double> {
    public NegationExpression(Expression expr){
        super("-", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate(){
        return null;//change
    }
}
