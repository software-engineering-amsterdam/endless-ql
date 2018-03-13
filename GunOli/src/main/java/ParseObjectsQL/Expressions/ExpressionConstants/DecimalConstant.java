package ParseObjectsQL.Expressions.ExpressionConstants;

import ParseObjectsQL.Expressions.EvaluationType;

public class DecimalConstant extends Constant<Double> {
    public DecimalConstant(Double value){
        super(value == null ? 0.0 : value);
    }
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
