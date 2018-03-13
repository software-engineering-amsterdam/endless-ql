package ParseObjectsQL.Expressions.ExpressionConstants;

import ParseObjectsQL.Expressions.EvaluationType;

public class DateConstant extends Constant<String> {
    public DateConstant(String value){
        super(value);
    }

    public EvaluationType returnType(){
        return EvaluationType.Date;
    }
}
