package ParseObjects.Expressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;

public abstract class Expression<T> {
    public abstract EvaluationType returnType();
    public abstract Constant<T> evaluate();

    public Boolean isArithmetic(){
        return false;
    }

    public Boolean isLogical(){
        return false;
    }
}