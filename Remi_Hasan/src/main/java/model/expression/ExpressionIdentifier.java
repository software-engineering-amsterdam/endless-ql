package model.expression;

import evaluation.IASTVisitor;
import org.antlr.v4.runtime.Token;

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
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ExpressionIdentifier){
            ExpressionIdentifier otherExpressionIdentifier = (ExpressionIdentifier) other;
            return this.identifier.equals(otherExpressionIdentifier.identifier);
        }
        return false;
    }
}
