package expression;

import expression.variable.ExpressionVariable;
import model.LookupTable;

public class ExpressionIdentifier extends Expression {

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
        return LookupTable.getInstance().getQuestion(identifier).type;
    }

    @Override
    public ExpressionVariable evaluate() {
        return LookupTable.getInstance().getQuestionAnswer(identifier).evaluate();
    }

    @Override
    public boolean equals(Object other) {
        return evaluate().equals(other);
    }

    @Override
    public void typeCheck() { }
}
