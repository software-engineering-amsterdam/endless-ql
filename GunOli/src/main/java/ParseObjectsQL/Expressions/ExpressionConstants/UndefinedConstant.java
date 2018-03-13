package ParseObjectsQL.Expressions.ExpressionConstants;

import ParseObjectsQL.Expressions.EvaluationType;

public class UndefinedConstant extends Constant<Object> {
    private EvaluationType type;

    public UndefinedConstant(){
        super(null);
        setType(EvaluationType.Undefined);
    }

    public UndefinedConstant(EvaluationType type){
        super(null);
        setType(type);
    }

    public EvaluationType returnType() { return type; }

    public void setType(EvaluationType type) { this.type = type; }
}
