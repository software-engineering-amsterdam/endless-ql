package org.uva.sea.ql.evaluate.valueTypes;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.visitor.BaseValueVisitor;

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
