package expression;

import model.Form;

public class ExpressionIdentifier extends Expression<Object>{

    private final String identifier;

    public ExpressionIdentifier(String identifier){
        this.identifier = identifier;
    }

    // TODO do not return generic Object?
    @Override
    public Object evaluate(Form form) {
        if (isEvaluable(form)) {
            return getVariable(form).evaluate(form);
        }
        return new ExpressionVariableUndefined();
    }

    @Override
    public boolean isEvaluable(Form form) {
        return getVariable(form).isEvaluable(form);
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public ReturnType getReturnType(Form form){
        return form.block.getQuestionAnswer(identifier).getReturnType(form);
    }

    private Expression getVariable(Form form){
        return form.block.getQuestionAnswer(identifier);
    }

    @Override
    public Double divide(Form form, Expression other) {
        return getVariable(form).divide(form, other);
    }

    @Override
    public Double multiply(Form form, Expression other) {
        return getVariable(form).multiply(form, other);
    }

    @Override
    public Double subtract(Form form, Expression other) {
        return getVariable(form).subtract(form, other);
    }

    @Override
    public Double sum(Form form, Expression other) {
        return getVariable(form).sum(form, other);
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        return getVariable(form).equals(form, other);
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        return getVariable(form).ge(form, other);
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        return getVariable(form).gt(form, other);
    }

    @Override
    public Boolean le(Form form, Expression other) {
        return getVariable(form).le(form, other);
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        return getVariable(form).lt(form, other);
    }
}
