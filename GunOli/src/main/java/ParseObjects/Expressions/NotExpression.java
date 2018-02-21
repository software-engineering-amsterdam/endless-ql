package ParseObjects.Expressions;

public class NotExpression extends UnaryExpression<Boolean>{
    public NotExpression(Expression expr){
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
