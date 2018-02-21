package expression;

import model.LookupTable;

public class ExpressionIdentifier<T> extends Expression<T> {

    private final String identifier;

    public ExpressionIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public ReturnType getReturnType() {
        return evaluate().getReturnType();
    }

    @Override
    public ExpressionVariable evaluate() {
        return LookupTable.getInstance().getQuestionAnswer(identifier).evaluate();
    }
}
