package ParseObjects.Expressions;

public class NotExpression extends UnaryExpression<Boolean>{
    public NotExpression(Expression expr){
        super("!", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate(){
        return null;//change
    }
}
