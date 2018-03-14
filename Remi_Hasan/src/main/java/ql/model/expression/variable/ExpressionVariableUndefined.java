package ql.model.expression.variable;

import ql.evaluation.Binding;
import ql.evaluation.IExpressionVisitor;
import ql.model.expression.ExpressionVariable;
import ql.model.expression.ReturnType;
import org.antlr.v4.runtime.Token;

import java.util.List;

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
    public <T> T accept(IExpressionVisitor<T> visitor, List<Binding> bindings) {
        return visitor.visit(this, bindings);
    }
}