package ql.model.expression.variable;

import ql.IQLVisitor;

import org.antlr.v4.runtime.Token;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    public ExpressionVariableString(Token start, String value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}