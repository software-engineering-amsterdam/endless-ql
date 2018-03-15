package ql.model;

import org.antlr.v4.runtime.Token;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class Question extends Node {

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression computedAnswer;
    public final Expression condition;
    private final boolean isComputed;

    public Question(Token start, ReturnType type, String name, String text, Expression condition) {
        super(start);
        this.type = type;
        this.name = name;
        this.text = text;
        this.computedAnswer = null;
        this.isComputed = false;
        this.condition = condition;
    }

    public Question(Token start, ReturnType type, String name, String text, Expression defaultAnswer, Expression condition) {
        super(start);
        this.type = type;
        this.name = name;
        this.text = text;
        this.computedAnswer = defaultAnswer;
        this.isComputed = true;
        this.condition = condition;
    }

    public boolean isComputed() {
        return isComputed;
    }
}
