package ParseObjects.Expressions;

public class NegationExpression extends UnaryExpression<Boolean> {
    public NegationExpression(Expression expr){
        super("!", expr);
    }

    @Override
    public EvaluationType returnType(){
        return null;
    }

    @Override
    public Boolean evaluate(){
        return null;
    }
}
