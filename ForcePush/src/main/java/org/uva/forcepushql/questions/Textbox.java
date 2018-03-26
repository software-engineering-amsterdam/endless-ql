package org.uva.forcepushql.questions;

public class Textbox extends Question
{

    private String answer;
    private boolean calculation;

    public Textbox(String question, String answerType, String answerName)
    {
        super(question, answerType, answerName);
        answer = "";
        calculation = false;
    }

    public void givenAnswer(String answer)
    {
        this.answer = answer;
    }

    public String answerValue()
    {
        return answer;
    }

    public boolean hasCalculation(){
        return calculation;
    }

    public void setHasCalculation(boolean bool){
        calculation = bool;
    }

}
