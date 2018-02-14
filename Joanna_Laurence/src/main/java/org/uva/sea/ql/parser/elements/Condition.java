package org.uva.sea.ql.parser.elements;

import java.util.List;

public class Condition extends Expr {

    private String expression;
    private List<Question> questions;

    public Condition() {
        System.out.println("Condition created");
    }

    public Condition(String expression, List<Question> questions) {

        this.expression = expression;
        this.questions = questions;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
