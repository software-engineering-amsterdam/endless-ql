package ParseObjects.Expressions.ExpressionConstants;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

class BooleanConstant extends Constant<Boolean> {
    public BooleanConstant(Boolean value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    public Boolean greaterThan(){
        return false;
    }

    public Boolean greaterOrEqual(){
        return false;
    }

    public Boolean lessThan(){
        return false;
    }

    public Boolean lessOrEqual(){
        return false;
    }

    public Boolean and(){
        return false;
    }

    public Boolean or(){
        return false;
    }

    public Boolean equal(){
        return false;
    }

    public Boolean notEqual(){
        return false;
    }
}
