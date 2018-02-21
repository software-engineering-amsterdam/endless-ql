package model;

import expression.Expression;

public class Question extends Statement {

    public final String name;
    public final String text;
    public final Expression answer;

    public Question(String name, String text, Expression answer) {
        this.name = name;
        this.text = text;
        this.answer = answer;
    }

    @Override
    public boolean isQuestion() {
        return true;
    }
}
