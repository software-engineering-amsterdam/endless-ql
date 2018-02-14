package org.uva.sea.ql.parser.elements;

public class Statement extends Expr {
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
}
