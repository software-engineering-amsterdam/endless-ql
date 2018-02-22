package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

public class IntegerConstant extends Constant<Integer> {
    public IntegerConstant(Integer value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Integer;
    }
}
