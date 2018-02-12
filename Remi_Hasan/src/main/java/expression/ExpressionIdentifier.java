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
            Expression expression = form.block.getQuestionAnswer(identifier);
            return expression.evaluate(form);
        }
        return new ExpressionUndefined();
    }

    @Override
    public boolean isEvaluable(Form form) {
        Expression answer = form.block.getQuestionAnswer(identifier);
        return answer.isEvaluable(form);
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public boolean isNumber(Form form){
        return form.block.getQuestionAnswer(identifier).isNumber(form);
    }
}
