package ParseObjects.Expressions;

public class NegationExpression extends UnaryExpression<Double> {
    public NegationExpression(Expression expr){
        super("-", expr);
    }

    @Override
    public EvaluationType returnType(){
        return null;
    }

    @Override
    public Double evaluate(){
        return null;
    }
}
