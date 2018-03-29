package ql.model.expression.variable;

import ql.IQLVisitor;

import org.antlr.v4.runtime.Token;

public class ExpressionVariableInteger extends ExpressionVariable<Integer> {

    public ExpressionVariableInteger(Integer value) {
        super(value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
