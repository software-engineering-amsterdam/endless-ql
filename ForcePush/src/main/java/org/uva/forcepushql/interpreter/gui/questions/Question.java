package org.uva.forcepushql.interpreter.gui.questions;

import org.uva.forcepushql.interpreter.gui.Observer;
import org.uva.forcepushql.parser.ast.ValueType;

import java.util.LinkedList;

public abstract class Question
{

    private String question;
    private ValueType answerType;
    private String answerName;
    private LinkedList<Observer> observers;


    public Question(String question, ValueType answerType, String answerName)
    {
        this.question = question;
        this.answerType = answerType;
        this.answerName = answerName;
        observers = new LinkedList<Observer>();

    }

    public String writtenQuestion()
    {
        return question;
    }

    public String answerNameValue()
    {
        return answerName;
    }

    public void attachObserver(Observer observer)
    {
        observers.add(observer);
    }

    public LinkedList<Observer> getObservers() {
        return observers;
    }

    abstract public void notifyAllObservers();


}
