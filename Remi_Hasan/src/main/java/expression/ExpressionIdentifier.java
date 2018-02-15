package expression;

public class ExpressionIdentifier extends Expression<Object>{

    private final String identifier;

    public ExpressionIdentifier(String identifier){
        this.identifier = identifier;
    }

    @Override
    public ExpressionVariable evaluate() {
        return getVariable(form).evaluate();
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public ReturnType getReturnType(){
        return getVariable(form).getReturnType();
    }

    private Expression getVariable(){
        return form.getQuestionAnswer(identifier);
    }
}
