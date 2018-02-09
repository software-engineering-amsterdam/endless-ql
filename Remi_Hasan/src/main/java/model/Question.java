package model;

import answer.Answer;

public class Question {

    public final String name;
    public final String text;
    public final Answer answer;

    public Question(String name, String text, Answer answer) {
        this.name = name;
        this.text = text.substring(1, text.length() - 1);
        this.answer = answer;
    }
}
