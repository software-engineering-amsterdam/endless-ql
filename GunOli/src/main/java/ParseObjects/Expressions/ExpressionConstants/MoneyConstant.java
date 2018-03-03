package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.EvaluationType;

public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Number value){
        super(value.doubleValue());
    }
    public MoneyConstant(String value) {
        super(value.equals("") ? 0.0 : Double.parseDouble(value));
    }
    public EvaluationType returnType(){
        return EvaluationType.Money;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
