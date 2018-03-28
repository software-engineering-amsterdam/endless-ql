package ql.model.expression.variable;

import ql.IQLVisitor;

import org.antlr.v4.runtime.Token;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Boolean value) {
        super(value);
    }

    public ExpressionVariableBoolean(Token start, Boolean value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}