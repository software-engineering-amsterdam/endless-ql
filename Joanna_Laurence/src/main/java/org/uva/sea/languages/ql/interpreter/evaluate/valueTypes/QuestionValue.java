package org.uva.sea.languages.ql.interpreter.evaluate.valueTypes;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class QuestionValue extends Value {

    private Question question;

    public QuestionValue(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return this.question;
    }

    @Override
    public <T> T accept(BaseValueVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.UNKNOWN;
    }
}
