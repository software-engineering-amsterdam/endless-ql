package model;

import expression.Expression;
import expression.ExpressionUndefined;

import java.util.ArrayList;

public class Block {

    public final ArrayList<Question> questions;

    public Block(ArrayList<Question> questions){
        this.questions = questions;
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
    }

    public Expression getQuestionAnswer(String name){
        for(Question question : questions){
            if(question.name.equals(name)){
                return question.answer;
            }
        }
        return new ExpressionUndefined();
    }
}
