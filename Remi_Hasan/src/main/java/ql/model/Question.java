package ql.model;

import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class Question extends Statement {

    public final ReturnType type;
    public final String identifier;
    public final String label;
    public final Expression computedAnswer;

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

    public boolean isComputed() {
        return this.computedAnswer != null;
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
