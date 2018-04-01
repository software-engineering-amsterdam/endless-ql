package org.uva.forcepushql.interpreter.gui.questions;

import org.uva.forcepushql.interpreter.gui.Observer;
import org.uva.forcepushql.parser.ast.ValueType;

public class Textbox extends Question
{

    private String answer;
    private boolean calculation;

    public Textbox(String question, ValueType answerType, String answerName)
    {
        super(question, answerType, answerName);
        answer = "";
        calculation = false;
    }

    @Override
    public void notifyAllObservers() {
        for (Observer obs : getObservers())
        {
            obs.updateTextbox(this);
        }
    }

    public void givenAnswer(String answer)
    {
        this.answer = answer;
        notifyAllObservers();
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
