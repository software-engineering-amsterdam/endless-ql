package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

public class BooleanConstant extends Constant<Boolean> {
    public BooleanConstant(Boolean value) { super(value); }
    public BooleanConstant(String value)  { super(Boolean.parseBoolean(value)); }

    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }
}
