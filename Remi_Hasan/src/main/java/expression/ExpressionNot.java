package expression;

import model.Form;

public class ExpressionNot extends Expression<Boolean> {

    private final Expression value;

    public ExpressionNot(Expression value){
        this.value = value;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return this.value.isEvaluable(form) && this.value.getReturnType(form) == ReturnType.Boolean;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }

    @Override
    public Boolean evaluate(Form form) {
        if(isEvaluable(form)){
            ExpressionVariableBoolean rewrittenExpression = new ExpressionVariableBoolean(!(boolean)this.value.evaluate(form));
            return rewrittenExpression.evaluate(form);
        }
        return null;
    }

    @Override
    public Double divide(Form form, Expression other) {
        return null;
    }

    @Override
    public Double multiply(Form form, Expression other) {
        return null;
    }

    @Override
    public Double subtract(Form form, Expression other) {
        return null;
    }

    @Override
    public Double sum(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean le(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        return null;
    }
}
