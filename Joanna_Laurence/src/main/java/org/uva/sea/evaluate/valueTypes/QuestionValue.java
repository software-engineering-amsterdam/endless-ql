package org.uva.sea.evaluate.valueTypes;

import org.uva.sea.ql.NodeType;
import org.uva.sea.ql.elements.Question;
import org.uva.sea.visitor.BaseValueVisitor;

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
