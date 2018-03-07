package expression;

import astvisitor.IASTVisitor;

public class ExpressionIdentifier extends Expression {

    public final String identifier;

    public ExpressionIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.UNDEFINED;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
