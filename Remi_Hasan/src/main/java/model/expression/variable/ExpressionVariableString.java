package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(Token start, String value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionVariableString;
    }
}