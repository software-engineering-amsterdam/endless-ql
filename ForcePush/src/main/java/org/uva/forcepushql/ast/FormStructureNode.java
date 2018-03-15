package org.uva.forcepushql.ast;

import java.util.LinkedList;


public abstract class FormStructureNode extends Node{ }

class FormNode extends FormStructureNode{
    private LinkedList<Node> questions;
    private String name;

    public FormNode(){
        questions = new LinkedList<Node>();
    }

    public LinkedList<Node> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Node> questions) {
        this.questions = questions;
    }

    public void setOneQuestion(Node question){
        if (question != null)
            questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

