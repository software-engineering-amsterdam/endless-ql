package ql.model.expression;

import ql.evaluation.Binding;
import ql.evaluation.IExpressionVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ExpressionIdentifier extends Expression {

    public final String identifier;

    public ExpressionIdentifier(Token start, String identifier) {
        super(start);
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor, List<Binding> bindings) {
        return visitor.visit(this, bindings);
    }
}
