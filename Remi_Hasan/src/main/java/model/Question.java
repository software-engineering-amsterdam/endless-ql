package model;

import expression.Expression;
import expression.ExpressionVariableBoolean;

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
            System.out.println("c: " + condition.toString() + " " + Boolean.TRUE.equals(condition.evaluate(form).get()));
            if(!Boolean.TRUE.equals(condition.evaluate(form).get()) && name.equals("someBoolean4")){
                System.out.println("");
            }
            conditionsMet &=  Boolean.TRUE.equals(condition.evaluate(form).get());
        }
        System.out.println("q: " + name + " " + conditionsMet);
        return conditionsMet && answer.isSetable(form);
    }
}
