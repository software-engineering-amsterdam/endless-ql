package expression;

import answer.Answer;
import model.Form;

public class ExpressionIdentifier extends Expression<Object>{

    private final String identifier;

    public ExpressionIdentifier(String identifier){
        this.identifier = identifier;
    }

    @Override
    public Object evaluate(Form form) {
        System.out.println("id: " + identifier + " value: " + form.block.getQuestionAnswer(identifier).getValue());
        if (isEvaluable(form)) {
            return form.block.getQuestionAnswer(identifier).getValue();
        }
        return new ExpressionUndefined();
    }

    @Override
    public boolean isEvaluable(Form form) {
        Answer answer = form.block.getQuestionAnswer(identifier);
        return answer != null && answer.getValue() != null;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
