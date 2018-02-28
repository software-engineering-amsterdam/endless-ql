package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Double value){
        super(value);
    }
    public MoneyConstant(String value) { super(Double.parseDouble(value)); }

    public EvaluationType returnType(){
        return EvaluationType.Money;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
