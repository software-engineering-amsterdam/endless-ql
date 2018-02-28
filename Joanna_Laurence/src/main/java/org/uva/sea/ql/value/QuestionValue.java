package org.uva.sea.ql.value;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Question;

public class QuestionValue extends Value {

    private Question question;

    public QuestionValue(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return this.question;
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public NodeType getType() {
        return NodeType.UNKNOWN;
    }
}
