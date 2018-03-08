package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Token start, Integer value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionVariableInteger;
    }
}
