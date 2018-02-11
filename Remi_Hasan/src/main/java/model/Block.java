package model;

import expression.Expression;
import expression.ExpressionUndefined;

import java.util.ArrayList;

public class Block {

    public final ArrayList<Question> questions;
    public final ArrayList<Condition> conditions;

    public Block(ArrayList<Question> questions, ArrayList<Condition> conditions){
        this.questions = questions;
        this.conditions = conditions;
    }

    public void answer(String name, String answer){
        for(Question question : questions){
            if(question.name.equals(name)){
                try{
                    System.out.println("answering: " + name);
                    question.answer.setValue(answer);
                } catch(Exception e){
                    // TODO what to do when it fails?
                }
            }
        }
        for(Condition condition: conditions){
            condition.block.answer(name, answer);
        }
    }

    public Expression getQuestionAnswer(String name){
        System.out.println("getQuestionAnswer: " + name);
        for(Question question : questions){
            if(question.name.equals(name)){
                System.out.println("found: " + question.answer);
                return question.answer;
            }
        }
        for(Condition condition : conditions){
            Expression answer = condition.block.getQuestionAnswer(name);
            if(answer != null){
                return answer;
            }
        }
        return new ExpressionUndefined();
    }
}
