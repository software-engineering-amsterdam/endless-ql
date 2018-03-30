package org.uva.forcepushql.interpreter.gui.questions;


public class Rating extends Question
{

    private int answer;

    public Rating(String question, String answerType, String answerName)
    {
        super(question, answerType, answerName);
        answer = 0;
    }

    @Override
    public void notifyAllObservers() {

    }

    public void givenAnswer(int answer)
    {
        this.answer = answer;
    }

    public int answerValue()
    {
        return answer;
    }

}
