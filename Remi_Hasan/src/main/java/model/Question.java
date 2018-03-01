package model;

import expression.Expression;
import expression.ReturnType;

public class Question{

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression answer;

    public Question(ReturnType type, String name, String text, Expression answer) {
        this.type = type;
        this.name = name;
        this.text = text;
        this.answer = answer;
    }
}
