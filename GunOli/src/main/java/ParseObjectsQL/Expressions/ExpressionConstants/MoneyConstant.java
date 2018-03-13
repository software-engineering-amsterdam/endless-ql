package ParseObjectsQL.Expressions.ExpressionConstants;

import ParseObjectsQL.Expressions.EvaluationType;

public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Number value){
        super(value == null ? 0.0 : value.doubleValue());
    }

    public EvaluationType returnType(){
        return EvaluationType.Money;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
