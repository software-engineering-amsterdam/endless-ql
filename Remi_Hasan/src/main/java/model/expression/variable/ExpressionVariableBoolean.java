package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Token start, Boolean value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionVariableBoolean;
    }
}