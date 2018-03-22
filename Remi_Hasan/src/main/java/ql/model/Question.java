package ql.model;

import org.antlr.v4.runtime.Token;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class Question extends QLNode {

    public final ReturnType type;
    public final String identifier;
    public final String label;
    public final Expression computedAnswer;
    public final Expression condition;
    private final boolean isComputed;

    public Question(Token start, ReturnType type, String identifier, String label, Expression condition) {
        super(start);
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = null;
        this.isComputed = false;
        this.condition = condition;
    }

    public Question(Token start, ReturnType type, String identifier, String label, Expression defaultAnswer, Expression condition) {
        super(start);
        this.type = type;
        this.identifier = identifier;
        this.label = label;
        this.computedAnswer = defaultAnswer;
        this.isComputed = true;
        this.condition = condition;
    }

    public boolean isComputed() {
        return isComputed;
    }
}
