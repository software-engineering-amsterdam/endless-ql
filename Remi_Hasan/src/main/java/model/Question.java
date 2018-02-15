package model;

import expression.Expression;
import expression.ExpressionVariableBoolean;

import java.util.ArrayList;

public class Question extends BlockElement{

    public final String name;
    public final String text;
    public final Expression answer;

    public Question(String name, String text, Expression answer) {
        this.name = name;
        this.text = text.substring(1, text.length() - 1);
        this.answer = answer;
    }

    @Override
    public boolean isQuestion() {
        return true;
    }
}
