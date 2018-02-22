package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionValue extends Value {

    List<Question> question = new ArrayList<>();

    public List<Question> getQuestion() {
        return this.question;
    }

    @Override
    public <T> T accept(QLValueEvaluator<T> visitor) {
        return visitor.visit(this);
    }

}
