package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class QuestionValue extends Value {

    private final Question question;

    public QuestionValue(final Question question) {
        this.question = question;
    }

    public final Question getQuestion() {
        return this.question;
    }

    @Override
    public final <T> T accept(final BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public final NodeType getType() {
        return NodeType.UNKNOWN;
    }

    public final QuestionValue clone() throws CloneNotSupportedException {
        return (QuestionValue) super.clone();
    }
}
