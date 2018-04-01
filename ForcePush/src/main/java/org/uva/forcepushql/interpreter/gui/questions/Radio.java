package org.uva.forcepushql.interpreter.gui.questions;


import org.uva.forcepushql.interpreter.gui.Observer;
import org.uva.forcepushql.parser.ast.ValueType;

public class Radio extends Question
{

    private boolean answer;

    public Radio(String question, ValueType answerType, String answerName)
    {
        super(question, answerType, answerName);
        answer = false;
    }

    @Override
    public void notifyAllObservers() {
        for (Observer obs : getObservers())
        {
            obs.updateRadio(this);
        }

    }

    public void givenAnswer(boolean answer)
    {
        this.answer = answer;
        notifyAllObservers();
    }

    public boolean answerValue()
    {
        return answer;
    }

}
