package ParseObjects.Expressions;

public class SubtractExpression extends BinaryExpression<Double> {
    public SubtractExpression(String operator, Expression left, Expression right){
        super(operator, left, right);
    }


    @Override
    public EvaluationType returnType() {
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate() {
        //ToDo: Implement
        return null;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
