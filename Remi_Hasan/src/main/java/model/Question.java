package model;

import expression.Expression;

import java.util.ArrayList;

public class Question{

    public final String name;
    public final String text;
    public final Expression answer;
    public ArrayList<Expression> conditions = new ArrayList<>();

    public Question(String name, String text, Expression answer) {
        this.name = name;
        this.text = text.substring(1, text.length() - 1);
        this.answer = answer;
    }

    public void addCondition(Expression condition) {
        this.conditions.add(condition);
    }

    public boolean isAnswerable(Form form){
        boolean conditionsMet = true;
        for(Expression condition : conditions){
            conditionsMet &=  Boolean.TRUE.equals(condition.evaluate(form));
        }
        return conditionsMet && answer.isSetable(form);
    }
}
