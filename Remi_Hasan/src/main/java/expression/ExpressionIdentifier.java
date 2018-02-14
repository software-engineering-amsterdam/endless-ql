package expression;

import model.Form;

public class ExpressionIdentifier extends Expression<Object>{

    private final String identifier;

    public ExpressionIdentifier(String identifier){
        this.identifier = identifier;
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return getVariable(form).evaluate(form);
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public ReturnType getReturnType(Form form){
        return getVariable(form).getReturnType(form);
    }

    private Expression getVariable(Form form){
        return form.block.getQuestionAnswer(identifier);
    }
}
