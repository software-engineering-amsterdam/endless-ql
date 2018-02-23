package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

public class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value){
        super(value);
    }
    public DecimalConstant(String value) { super(Double.parseDouble(value)); }

    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }
}
