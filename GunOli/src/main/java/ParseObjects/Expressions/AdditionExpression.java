package ParseObjects.Expressions;

public class AdditionExpression extends BinaryExpression<Double> {

    public AdditionExpression(String operator, Expression left, Expression right){
        super(operator, left, right);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Double evaluate(){
        return null;//change
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}

