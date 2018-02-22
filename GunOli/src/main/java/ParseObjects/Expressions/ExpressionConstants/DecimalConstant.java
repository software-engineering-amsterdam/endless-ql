package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }
}
