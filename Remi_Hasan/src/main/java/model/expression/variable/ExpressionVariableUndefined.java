package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import model.expression.ReturnType;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableUndefined extends ExpressionVariable<ReturnType> {

    public ExpressionVariableUndefined(Token start, ReturnType value) {
        super(start, value);
    }

    public ReturnType getReturnType() {
        if (this.value.isNumber()) {
            return ReturnType.NUMBER;
        }

        return this.value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionVariableUndefined;
    }
}