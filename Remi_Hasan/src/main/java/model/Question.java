package model;

import expression.Expression;
import expression.ReturnType;
import expression.variable.ExpressionVariableBoolean;

public class Question{

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression answer;
    public final Expression condition;

    public Question(ReturnType type, String name, String text, Expression answer, Expression condition) {
        this.type = type;
        this.name = name;
        this.text = text;
        this.answer = answer;
        this.condition = condition;
    }
}
