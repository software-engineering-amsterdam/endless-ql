package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.traverse.Traverse;

public class Statement implements ASTNode {
    private Question question;
    private Condition condition;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void traverse(Traverse traverse) {
        traverse.doStatement(this);
    }
}
