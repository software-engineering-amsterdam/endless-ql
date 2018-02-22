package ParseObjects.Expressions;

public class SubtractExpression extends BinaryExpression<Integer> {

    public SubtractExpression(String operator, Expression left, Expression right){
        super(operator, left, right);
    }


    @Override
    public EvaluationType returnType() {
        return EvaluationType.Integer;
    }

    @Override
    public Constant evaluate() {
        //ToDo: Implement
        return null;
    }

    @Override
    public Boolean isArithmetic(){

        return true;
    }
}
