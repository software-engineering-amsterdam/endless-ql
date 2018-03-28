package ql.model.statement;

import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class Question extends Statement {

    private final ReturnType type;
    private final String identifier;
    private final String label;
    private final Expression computedAnswer;

    public Question(Token start, ReturnType type, String identifier, String label) {
        super(start);
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = null;
    }

    public Question(Token start, ReturnType type, String identifier, String label, Expression defaultAnswer) {
        super(start);
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = defaultAnswer;
    }

    public ReturnType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public Expression getComputedAnswer() {
        return computedAnswer;
    }

    public boolean isComputed() {
        return this.computedAnswer != null;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
