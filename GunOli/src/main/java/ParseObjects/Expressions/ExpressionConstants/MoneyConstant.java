package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

/**
 * Created by Michael on 2/22/2018.
 */
public class MoneyConstant extends Constant<Double> {
    public MoneyConstant(Double value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Money;
    }
}
